package com.delete.test.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.delete.test.presentation.navigation.BottomBar
import com.delete.test.presentation.navigation.NavigationTabs
import com.delete.test.presentation.navigation.TopBar
import com.delete.test.presentation.navigation.buildTabs
import com.delete.test.presentation.screen.currency.CurrencyViewModel
import com.delete.test.presentation.theme.MainTheme

@Composable
fun TestApp() {
    val tabs = remember { NavigationTabs.entries.toTypedArray() }
    val navController = rememberNavController()
    val viewModel: CurrencyViewModel = hiltViewModel()

    MainTheme {

        Scaffold(
            topBar = {
                TopBar(navController = navController,tabs = tabs)
            },
            bottomBar = {
                BottomBar(navController = navController, tabs = tabs)
            }
        ) { innerPaddingModifier ->

            NavHost(
                modifier = Modifier.padding(innerPaddingModifier),
                navController = navController,
                startDestination = NavigationTabs.CURRENCY.route
            ) {
                buildTabs( navController, viewModel)
            }
        }
    }
}