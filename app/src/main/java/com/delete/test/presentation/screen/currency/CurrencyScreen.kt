package com.delete.test.presentation.screen.currency

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.delete.test.domain.model.entity.CurrencyFavorite

@Composable
fun CurrencyScreen(
    navController: NavController,
    viewModel: CurrencyViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    Column(modifier = Modifier.fillMaxSize()) {

        CurrencyHeader(navController, viewModel)
        LazyColumn {
            items(state.displayCurrencies) { currency ->
                CurrencyItem(currency.tag, currency.rate, false) {
                    viewModel.onFavoriteChanged(
                        CurrencyFavorite(
                            currencyFrom = state.base,
                            currencyTo = currency.tag,
                            rate = currency.rate,
                        ),
                        true
                    )
                }
            }
        }
    }

}