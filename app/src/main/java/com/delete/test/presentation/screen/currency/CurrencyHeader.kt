package com.delete.test.presentation.screen.currency

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.delete.test.R
import com.delete.test.presentation.navigation.NavigationTabs
import com.delete.test.presentation.theme.AppTheme

@Composable
fun CurrencyHeader(navController: NavController, viewModel: CurrencyViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsStateWithLifecycle()


    Box(
        modifier = Modifier
            .shadow(10.dp)
            .background(AppTheme.colors.header)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .border(
                        1.dp,
                        AppTheme.colors.secondary,
                        AppTheme.shapes.secondaryShape
                    )
                    .background(AppTheme.colors.primaryBackground)
                    .weight(1f)
                    .height(48.dp)
                    .clickable { expanded = true }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = state.base, style = AppTheme.typography.primaryTypography)
                Icon(
                    painterResource(R.drawable.icon_down),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.width(8.dp))
            IconButton(
                onClick = {
                    navController.navigate(NavigationTabs.FILTERS.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true

                    }

                },
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        AppTheme.colors.primaryBackground,
                        AppTheme.shapes.secondaryShape
                    )
                    .border(
                        1.dp,
                        AppTheme.colors.secondary,
                        AppTheme.shapes.secondaryShape
                    )
            ) {
                Icon(
                    painterResource(R.drawable.icon_filter),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .padding(start = 8.dp, end = 64.dp)
                .border(
                    1.dp,
                    AppTheme.colors.secondary,
                    AppTheme.shapes.secondaryShape
                )
                .fillMaxWidth()
                .background(
                    AppTheme.colors.primaryBackground,
                    AppTheme.shapes.secondaryShape
                )

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                state.headerCurrencies.forEach { currency ->
                    Text(
                        currency, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onBaseChanged(currency)
                                expanded = false
                            }
                            .padding(16.dp, 4.dp),
                        style = AppTheme.typography.primaryTypography
                    )
                }

            }

        }

    }

}