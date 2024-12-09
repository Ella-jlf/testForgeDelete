package com.delete.test.presentation.screen.currency

import com.delete.test.domain.model.entity.CurrencyFavorite
import com.delete.test.domain.model.entity.CurrencyRate

data class CurrencyViewState(
    val base: String = "USD",
    val sort: Int = 0,
    val kostyl: Boolean = false,
    val headerCurrencies: List<String> = emptyList(),
    val displayCurrencies: List<CurrencyRate> = emptyList(),
    val favoriteCurrencies: List<CurrencyFavorite> = emptyList()
)