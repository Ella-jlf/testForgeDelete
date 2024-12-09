package com.delete.test.domain.repository

import arrow.core.Either
import com.delete.test.domain.model.entity.Currency
import com.delete.test.domain.model.entity.CurrencyFavorite
import com.delete.test.domain.model.response.CurrencyInfo
import com.delete.test.domain.model.entity.CurrencyRate
import com.delete.test.domain.model.response.NetworkError
import com.delete.test.domain.model.response.RateInfo
import kotlinx.coroutines.flow.Flow

interface CurrencyRepo {
    //Remote Currency
    suspend fun getRemoteCurrencies(): Either<NetworkError, CurrencyInfo>

    //Local Currency
    suspend fun deleteLocalCurrencies()

    suspend fun getLocalCurrencies(): List<Currency>
    suspend fun getLocalCurrencies(sort: Int): Flow<List<Currency>>

    suspend fun saveLocalCurrencies(currencies: List<Currency>)

    //Remote Rate
    suspend fun getRemoteRateCurrencies(
        base: String,
        others: List<String>
    ): Either<NetworkError, RateInfo>

    //Local Rate
    suspend fun deleteLocalRateCurrencies()

    suspend fun getLocalRateCurrencies(sort: Int): Flow<List<CurrencyRate>>

    suspend fun saveLocalRateCurrencies(currencyRates: List<CurrencyRate>)

    //Local Favorite
    suspend fun saveLocalFavoriteCurrency(currencyFavorite: CurrencyFavorite)

    suspend fun deleteLocalFavoriteCurrency(id: Int)
    suspend fun deleteLocalFavoriteCurrency(currencyFrom: String, currencyTo: String)

    suspend fun getLocalFavoriteCurrencies(sort: Int): Flow<List<CurrencyFavorite>>
}