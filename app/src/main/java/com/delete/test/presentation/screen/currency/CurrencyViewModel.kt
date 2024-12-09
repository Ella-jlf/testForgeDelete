package com.delete.test.presentation.screen.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delete.test.BuildConfig
import com.delete.test.domain.model.entity.Currency
import com.delete.test.domain.model.entity.CurrencyFavorite
import com.delete.test.domain.model.response.CurrencyInfo
import com.delete.test.domain.model.entity.CurrencyRate
import com.delete.test.domain.model.response.RateInfo
import com.delete.test.domain.repository.CurrencyRepo
import com.delete.test.util.combine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepo: CurrencyRepo,
) : ViewModel() {

    private val _sort = MutableStateFlow(1)
    private val _baseCurrency = MutableStateFlow("USD")

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _headerCurrencies = _sort.flatMapLatest { sort ->
        when (sort) {
            0 -> currencyRepo.getLocalCurrencies(0)
            1 -> currencyRepo.getLocalCurrencies(1)
            2 -> currencyRepo.getLocalCurrencies(2)
            3 -> currencyRepo.getLocalCurrencies(3)
            else -> currencyRepo.getLocalCurrencies(0)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _favoriteCurrencies = _sort.flatMapLatest { sort ->
        when (sort) {
            0 -> currencyRepo.getLocalFavoriteCurrencies(0)
            1 -> currencyRepo.getLocalFavoriteCurrencies(1)
            2 -> currencyRepo.getLocalFavoriteCurrencies(2)
            3 -> currencyRepo.getLocalFavoriteCurrencies(3)
            else -> currencyRepo.getLocalFavoriteCurrencies(0)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _rateCurrencies = _sort.flatMapLatest { sort ->
        when (sort) {
            0 -> currencyRepo.getLocalRateCurrencies(0)
            1 -> currencyRepo.getLocalRateCurrencies(1)
            2 -> currencyRepo.getLocalRateCurrencies(2)
            3 -> currencyRepo.getLocalRateCurrencies(3)
            else -> currencyRepo.getLocalRateCurrencies(0)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(CurrencyViewState())

    val state = combine(
        _sort,
        _headerCurrencies,
        _rateCurrencies,
        _favoriteCurrencies,
        _baseCurrency,
        _state
    ) { sort, headerCurrencies, rateCurrencies, favoriteCurrencies, base, state ->
        state.copy(
            headerCurrencies = headerCurrencies.map { it.tag }.filter { it != base },
            displayCurrencies = rateCurrencies,
            favoriteCurrencies = favoriteCurrencies,
            sort = sort,
            base = base,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), CurrencyViewState())

    init {
        getCurrencies()
        getRates()
    }

    fun onSortChanged(sort: Int) {
        _sort.value = sort
    }

    fun onBaseChanged(base: String) {
        _baseCurrency.value = base
        getRates()
    }

    fun onFavoriteChanged(currencyFavorite: CurrencyFavorite, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite) {
                currencyRepo.saveLocalFavoriteCurrency(currencyFavorite)
            } else {
                if (currencyFavorite.id == 0)
                    currencyRepo.deleteLocalFavoriteCurrency(
                        currencyFavorite.currencyFrom,
                        currencyFavorite.currencyTo
                    )
                else
                    currencyRepo.deleteLocalFavoriteCurrency(currencyFavorite.id)
            }
        }
    }

    private fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {

            currencyRepo.deleteLocalCurrencies()
            currencyRepo.getRemoteCurrencies()
                .onRight { currencyInfo: CurrencyInfo ->
                    currencyRepo.saveLocalCurrencies(
                        currencyInfo.currencies.map {
                            Currency(
                                tag = it.key,
                                description = it.value
                            )
                        })
                }
                .onLeft { error ->
                    if (BuildConfig.DEBUG) {
                        println("ERROR ${error.error}, REASON ${error.t}")
                    }
                }
        }
    }

    private fun getRates() {
        viewModelScope.launch(Dispatchers.IO) {

            currencyRepo.deleteLocalRateCurrencies()
            currencyRepo.getRemoteRateCurrencies(
                _baseCurrency.value,
                emptyList()
            ).onRight { rateInfo: RateInfo ->
                currencyRepo.saveLocalRateCurrencies(
                    rateInfo.rates.map {
                        CurrencyRate(
                            tag = it.key,
                            rate = it.value,
                            rateTo = _state.value.base,
                            description = ""
                        )
                    }
                )
            }
                .onLeft { error ->
                    if (BuildConfig.DEBUG) {
                        println("ERROR ${error.error}, REASON ${error.t}")
                    }
                }
        }
    }
}