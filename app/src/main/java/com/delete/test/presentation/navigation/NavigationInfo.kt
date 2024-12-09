package com.delete.test.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.delete.test.R
import com.delete.test.presentation.screen.currency.CurrencyScreen
import com.delete.test.presentation.screen.currency.CurrencyViewModel
import com.delete.test.presentation.screen.favorites.FavoritesScreen
import com.delete.test.presentation.screen.filters.FiltersScreen

fun NavGraphBuilder.buildTabs(
    navController: NavController,
    viewModel: CurrencyViewModel
) {

    composable(NavigationTabs.CURRENCY.route) {
        CurrencyScreen(navController, viewModel)
    }
    composable(NavigationTabs.FAVORITES.route) {
        FavoritesScreen(viewModel)
    }
    composable(NavigationTabs.FILTERS.route) {
        FiltersScreen(navController, viewModel)
    }

}

enum class NavigationTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    CURRENCY(
        R.string.nav_currency,
        R.drawable.icon_currency,
        TabsDestinations.CURRENCY
    ),
    FAVORITES(
        R.string.nav_favorites,
        R.drawable.icon_favorite,
        TabsDestinations.FAVORITES_ROUTE
    ),
    FILTERS(
        R.string.nav_filters,
        R.drawable.icon_filter,
        TabsDestinations.FILTERS_ROUTE
    )

}


private object TabsDestinations {
    const val CURRENCY = "tabs/currency"
    const val FAVORITES_ROUTE = "tabs/favorites"
    const val FILTERS_ROUTE = "tabs/filters"
}