package com.delete.test.presentation.screen.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.delete.test.presentation.screen.currency.CurrencyItem
import com.delete.test.presentation.screen.currency.CurrencyViewModel

@Composable
fun FavoritesScreen(viewModel: CurrencyViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn {
        items(state.favoriteCurrencies) { currency ->
            CurrencyItem(
                currency.currencyFrom + "/" + currency.currencyTo,
                currency.rate,
                true
            ) {
                viewModel.onFavoriteChanged(
                    currency,
                    false
                )
            }
        }
    }
}