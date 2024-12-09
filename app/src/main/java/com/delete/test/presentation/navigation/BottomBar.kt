package com.delete.test.presentation.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.delete.test.presentation.theme.AppTheme
import java.util.*

@Composable
fun BottomBar(navController: NavController, tabs: Array<NavigationTabs>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: NavigationTabs.CURRENCY

    val routes =
        remember { NavigationTabs.entries.filter { it != NavigationTabs.FILTERS }.map { it.route } }
    if (currentRoute in routes) {
        BottomNavigation(
            backgroundColor = AppTheme.colors.onPrimary,
            modifier = Modifier.height(68.dp)
        ) {
            tabs.forEach { tab ->

                if (tab == NavigationTabs.FILTERS){
                    return@forEach
                }

                BottomNavigationItem(

                    icon = {
                        Icon(
                            painterResource(tab.icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(tab.title),
                            style = AppTheme.typography.menuTypography,
                            color = if (currentRoute == tab.route) AppTheme.colors.primaryText else AppTheme.colors.secondary
                        )
                    },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = true,
                    selectedContentColor = AppTheme.colors.primary,
                    unselectedContentColor = AppTheme.colors.secondary,

                    )
            }
        }
    }
}