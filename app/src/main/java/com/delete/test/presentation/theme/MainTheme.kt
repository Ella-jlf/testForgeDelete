package com.delete.test.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MainTheme(
    content: @Composable () -> Unit
) {
    val colors = when (isSystemInDarkTheme()) {
        true -> {
            baseDarkPalette
        }
        false -> {
            baseLightPalette
        }
    }

    val shapes = baseShapes
    val typography = baseTypography

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppShapes provides shapes,
        LocalAppTypography provides typography,
        content = content,
    )


}