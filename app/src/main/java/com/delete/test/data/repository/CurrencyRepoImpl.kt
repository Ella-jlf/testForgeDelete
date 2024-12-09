package com.delete.test.data.repository

import arrow.core.Either
import com.delete.test.data.db.CurrencyDAO
import com.delete.test.data.mapper.toNetworkError
import com.delete.test.data.network.CurrencyAPI
import com.delete.test.domain.model.entity.Currency
import com.delete.test.domain.model.entity.CurrencyFavorite
import com.delete.test.domain.model.response.CurrencyInfo
import com.delete.test.domain.model.entity.CurrencyRate
import com.delete.test.domain.model.response.NetworkError
import com.delete.test.domain.model.response.RateInfo
import com.delete.test.domain.repository.CurrencyRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepoImpl @Inject constructor(
    private val currencyAPI: CurrencyAPI,
    private val currencyDAO: CurrencyDAO
) : CurrencyRepo {
    override suspend fun getRemoteCurrencies(): Either<NetworkError, CurrencyInfo> {
        return Either.catch {
            currencyAPI.getCurrencies()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getLocalCurrencies(): List<Currency> {
        return currencyDAO.getAll()
    }

    override suspend fun getLocalCurrencies(sort: Int): Flow<List<Currency>> {
        return when (sort) {
            0 -> currencyDAO.getAllSortedByTagASC()
            1 -> currencyDAO.getAllSortedByTagDESC()
            2 -> currencyDAO.getAllSortedByDescriptionASC()
            3 -> currencyDAO.getAllAllSortedByDescriptionDesc()
            else -> currencyDAO.getAllSortedByTagASC()
        }
    }

    override suspend fun saveLocalCurrencies(currencies: List<Currency>) {
        currencyDAO.insertCurrency(currencies)
    }

    override suspend fun deleteLocalCurrencies() {
        currencyDAO.delete()
    }

    override suspend fun getRemoteRateCurrencies(
        base: String,
        others: List<String>
    ): Either<NetworkError, RateInfo> {
        return Either.catch {
            currencyAPI.getRates(base, others)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun deleteLocalRateCurrencies() {
        currencyDAO.deleteRates()
    }

    override suspend fun getLocalRateCurrencies(sort: Int): Flow<List<CurrencyRate>> {
        return when (sort) {
            0 -> currencyDAO.getRateAllSortedByTagASC()
            1 -> currencyDAO.getRateAllSortedByTagDESC()
            2 -> currencyDAO.getRateAllSortedByDescriptionASC()
            3 -> currencyDAO.getRateAllAllSortedByDescriptionDesc()
            else -> currencyDAO.getRateAllSortedByTagASC()
        }
    }

    override suspend fun saveLocalRateCurrencies(currencyRates: List<CurrencyRate>) {
        currencyDAO.insertRate(currencyRates)
    }

    override suspend fun saveLocalFavoriteCurrency(currencyFavorite: CurrencyFavorite) {
        currencyDAO.insertCurrencyFavorite(currencyFavorite)
    }

    override suspend fun deleteLocalFavoriteCurrency(id: Int) {
        currencyDAO.deleteFavorite(id)
    }

    override suspend fun deleteLocalFavoriteCurrency(currencyFrom: String, currencyTo: String) {
        currencyDAO.deleteFavorite(currencyFrom, currencyTo)
    }

    override suspend fun getLocalFavoriteCurrencies(sort: Int): Flow<List<CurrencyFavorite>> {
        return when (sort) {
            0 -> currencyDAO.getFavoriteAllSortedByTagASC()
            1 -> currencyDAO.getFavoriteAllSortedByTagDESC()
            2 -> currencyDAO.getFavoriteAllSortedByDescriptionASC()
            3 -> currencyDAO.getFavoriteAllAllSortedByDescriptionDesc()
            else -> currencyDAO.getFavoriteAllSortedByTagASC()
        }
    }
}