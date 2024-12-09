package com.delete.test.data.network

import com.delete.test.domain.model.response.CurrencyInfo
import com.delete.test.domain.model.response.RateInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyAPI {

    @GET("symbols")
    suspend fun getCurrencies(): CurrencyInfo

    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String,
        @Query("symbols") others: List<String>
    ): RateInfo
}