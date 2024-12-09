package com.delete.test.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class AppColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val secondaryText: Color,
    val header: Color,
    val card: Color,
    val outline: Color,
    val chosen: Color,
    val primary: Color,
    val secondary: Color,
    val lightPrimary: Color,
    val onPrimary: Color,
)

data class AppShapes(
    val primaryShape: Shape,
    val secondaryShape: Shape,
    val primarySmallShape: Shape,
    val secondarySmallShape: Shape,
)

data class AppTypography(
    val primaryTypography: TextStyle,
    val secondaryTypography: TextStyle,
    val headerTypography: TextStyle,
    val menuTypography: TextStyle,
)

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
    val shapes: AppShapes
        @Composable
        get() = LocalAppShapes.current
    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("Colors not provided")
}
val LocalAppShapes = staticCompositionLocalOf<AppShapes> {
    error("Shapes not provided")
}
val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("Fonts not provided")
}