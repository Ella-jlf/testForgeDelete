package com.delete.test.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val baseLightPalette = AppColors(
    primaryText = Color(0xFF343138),
    primaryBackground = Color(0xFFFFFFFF),
    secondaryBackground = Color(0xFF9DACDC),
    secondaryText = Color(0xFF767676),
    primary = Color(0xFF0A2FA7),
    onPrimary = Color(0xFFFFFFFF),
    header = Color(0xFFF6F6F6),
    card = Color(0xFFF0F2F8),
    outline = Color(0xFFEBEBEB),
    chosen = Color(0xFFFFB800),
    secondary = Color(0xFF9DACDC),
    lightPrimary = Color(0xFFDEE4F8),
)

val baseDarkPalette = AppColors(
    primaryText = Color(0xFF343138),
    primaryBackground = Color(0xFFFFFFFF),
    secondaryBackground = Color(0xFF9DACDC),
    secondaryText = Color(0xFF767676),
    primary = Color(0xFF0A2FA7),
    onPrimary = Color(0xFFFFFFFF),
    header = Color(0xFFF6F6F6),
    card = Color(0xFFF0F2F8),
    outline = Color(0xFFEBEBEB),
    chosen = Color(0xFFFFB800),
    secondary = Color(0xFF9DACDC),
    lightPrimary = Color(0xFFDEE4F8),
)

val baseShapes = AppShapes(
    primaryShape = RoundedCornerShape(12.dp),
    secondaryShape = RoundedCornerShape(8.dp),
    primarySmallShape = RoundedCornerShape(16.dp),
    secondarySmallShape = RoundedCornerShape(32.dp),
)

val baseTypography = AppTypography(
    primaryTypography = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color(0xFF343138)),
    secondaryTypography = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color(0xFF767676)),
    headerTypography =TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF343138)),
    menuTypography =TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color(0xFF343138)),
)