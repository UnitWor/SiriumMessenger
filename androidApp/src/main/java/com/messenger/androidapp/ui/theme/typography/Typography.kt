package com.messenger.androidapp.ui.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.messenger.androidapp.R

val montserrat = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_semi_bold, FontWeight.SemiBold),
)

data class STypography(
    val x9l: TextStyle,
    val x8l: TextStyle,
    val x7l: TextStyle,
    val x6l: TextStyle,

    val large: TextStyle,
    val extraExtraSmall: TextStyle,
    val extraSmallMedium: TextStyle,

    val material: Typography,
)

val SiriumTypography = STypography(
    x9l = TextStyle(
        fontSize = 128.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
    ),
    x8l = TextStyle(
        fontSize = 96.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
    ),
    x7l = TextStyle(
        fontSize = 72.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
    ),
    x6l = TextStyle(
        fontSize = 60.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
    ),

    large = TextStyle(
        fontSize = 18.sp,
        lineHeight = 28.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal
    ),
    extraExtraSmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal
    ),
    extraSmallMedium = TextStyle(
        fontSize = 12.sp,
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium
    ),

    material = Typography(
        headlineLarge = TextStyle(
            fontSize = 48.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold
        ),
        headlineMedium = TextStyle(
            fontSize = 36.sp,
            lineHeight = 40.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium
        ),
        headlineSmall = TextStyle(
            fontSize = 30.sp,
            lineHeight = 36.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold
        ),

        // Titles
        titleLarge = TextStyle(
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium
        ),
        titleMedium = TextStyle(
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium
        ),
        titleSmall = TextStyle(
            fontSize = 18.sp,
            lineHeight = 28.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium
        ),

        // Body
        bodyLarge = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal
        ),
        bodyMedium = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Light
        ),
        bodySmall = TextStyle(
            fontSize = 12.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal
        ),

        // Labels
        labelLarge = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium
        ),
        labelMedium = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium
        ),
        labelSmall = TextStyle(
            fontSize = 10.sp,
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold
        )
    )
)
