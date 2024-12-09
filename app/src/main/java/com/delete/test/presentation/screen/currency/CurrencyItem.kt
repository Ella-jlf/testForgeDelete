package com.delete.test.presentation.screen.currency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.delete.test.R
import com.delete.test.presentation.theme.AppTheme

@Composable
fun CurrencyItem(name: String, rate: String, isFavorite: Boolean, onFavoriteClick: ()->Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .height(48.dp)
            .fillMaxWidth(),
        backgroundColor = AppTheme.colors.card,
        shape = AppTheme.shapes.primaryShape
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp, 2.dp)
        ) {
            Text(text = name, style = AppTheme.typography.primaryTypography)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = rate,
                    style = AppTheme.typography.primaryTypography.plus(TextStyle(fontWeight = FontWeight.Bold))
                )
                Spacer(Modifier.width(16.dp))
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        painterResource(if (isFavorite) R.drawable.icon_favorite else R.drawable.icon_favorite_off),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = if (isFavorite) AppTheme.colors.chosen else AppTheme.colors.secondary
                    )
                }
            }

        }
    }
}