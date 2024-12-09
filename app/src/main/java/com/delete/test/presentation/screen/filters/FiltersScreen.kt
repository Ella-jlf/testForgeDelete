package com.delete.test.presentation.screen.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.delete.test.R
import com.delete.test.presentation.screen.currency.CurrencyViewModel
import com.delete.test.presentation.theme.AppTheme

@Composable
fun FiltersScreen(navController: NavController, viewModel: CurrencyViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    val filters = listOf(
        stringResource(R.string.sort_0),
        stringResource(R.string.sort_1),
        stringResource(R.string.sort_2),
        stringResource(R.string.sort_3)
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(filters[state.sort]) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.sort_by),
            style = AppTheme.typography.secondaryTypography
        )
        Column(Modifier.selectableGroup()) {
            filters.forEach { text ->
                Row(
                    Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Text(
                        text = text,
                        style = AppTheme.typography.primaryTypography.plus(TextStyle(fontWeight = FontWeight.SemiBold))
                    )
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = AppTheme.colors.primary,
                            unselectedColor = AppTheme.colors.secondary
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                viewModel.onSortChanged(filters.indexOf(selectedOption))
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = AppTheme.shapes.secondarySmallShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primary)
        ) {
            Text(
                text = stringResource(R.string.apply),
                style = AppTheme.typography.primaryTypography.plus(TextStyle(color = AppTheme.colors.onPrimary))
            )
        }
    }
}