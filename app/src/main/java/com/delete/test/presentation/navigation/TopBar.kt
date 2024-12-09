package com.delete.test.presentation.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.delete.test.R
import com.delete.test.presentation.theme.AppTheme

@Composable
fun TopBar(navController: NavController, tabs: Array<NavigationTabs>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (navBackStackEntry?.destination?.route == NavigationTabs.FILTERS.route) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .size(48.dp)
                    ) {
                        Icon(
                            painterResource(R.drawable.icon_back),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = AppTheme.colors.primary
                        )
                    }
                }
                Text(
                    text = stringResource(
                        tabs.find { navBackStackEntry?.destination?.route == it.route }?.title
                            ?: NavigationTabs.CURRENCY.title
                    ),
                    style = AppTheme.typography.headerTypography,
                )
            }

        },
        backgroundColor = AppTheme.colors.header,
        elevation = 0.dp

    )
}