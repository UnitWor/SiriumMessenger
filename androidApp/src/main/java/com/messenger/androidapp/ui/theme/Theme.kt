package com.messenger.androidapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.messenger.androidapp.ui.theme.color.LightSiriumColor
import com.messenger.androidapp.ui.theme.typography.SiriumTypography

private val LightSiriumColorScheme = lightColorScheme(
    primary = LightSiriumColor.colorPrimary,
    secondary = LightSiriumColor.secondaryColor,
    tertiary = LightSiriumColor.secondaryColor2,
    background = LightSiriumColor.backPrimary,
    surface = LightSiriumColor.backSecondary,
    onPrimary = LightSiriumColor.white,
    onSecondary = LightSiriumColor.textPrimary,
    onBackground = LightSiriumColor.textPrimary,
    onSurface = LightSiriumColor.textPrimary,
    outline = LightSiriumColor.borderDisable
)

private val DarkSiriumColorScheme = darkColorScheme(
    primary = LightSiriumColor.colorPrimary,
    secondary = LightSiriumColor.secondaryColor,
    tertiary = LightSiriumColor.secondaryColor2,
    background = LightSiriumColor.backSecondary4,
    surface = LightSiriumColor.backSecondary,
    onPrimary = LightSiriumColor.white,
    onSecondary = LightSiriumColor.white,
    onBackground = LightSiriumColor.white,
    onSurface = LightSiriumColor.white
)

@Composable
fun SiriumTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkSiriumColorScheme
        else -> LightSiriumColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SiriumTypography,
        content = content
    )
}