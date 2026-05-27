package com.messenger.androidapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.messenger.androidapp.ui.theme.color.DarkSiriumColor
import com.messenger.androidapp.ui.theme.color.LightSiriumColor
import com.messenger.androidapp.ui.theme.color.SiriumColorScheme
import com.messenger.androidapp.ui.theme.typography.STypography
import com.messenger.androidapp.ui.theme.typography.SiriumTypography

private val lightMaterialColorScheme = lightColorScheme(
    primary = LightSiriumColor.colorPrimary,
    secondary = LightSiriumColor.secondaryColor,
    tertiary = LightSiriumColor.secondaryColor2,
    background = LightSiriumColor.backPrimary,
    surface = LightSiriumColor.backSecondary,
    onPrimary = LightSiriumColor.white,
    error = LightSiriumColor.red,
    onSecondary = LightSiriumColor.textPrimary,
    outline = LightSiriumColor.borderDisable,
)

private val darkMaterialColorScheme = darkColorScheme(
    primary = DarkSiriumColor.colorPrimary,
    secondary = DarkSiriumColor.secondaryColor,
    tertiary = DarkSiriumColor.secondaryColor2,
    background = DarkSiriumColor.backPrimary,
    surface = DarkSiriumColor.backSecondary,
    onPrimary = DarkSiriumColor.white,
    error = DarkSiriumColor.red,
    onSecondary = DarkSiriumColor.textPrimary,
    outline = DarkSiriumColor.borderDisable
)

val LightSiriumColorScheme = SiriumColorScheme(
    material = lightMaterialColorScheme,
    backSecondary4 = LightSiriumColor.backSecondary4,
    backSecondary3 = LightSiriumColor.backSecondary3,
    textSecondary = LightSiriumColor.textSecondary,
    sky2 = LightSiriumColor.sky2,
    green = LightSiriumColor.green,
    sky4 = LightSiriumColor.sky4
)

val DarkSiriumColorScheme = SiriumColorScheme(
    material = darkMaterialColorScheme,
    backSecondary4 = DarkSiriumColor.backSecondary4,
    backSecondary3 = DarkSiriumColor.backSecondary3,
    textSecondary = DarkSiriumColor.textSecondary,
    sky2 = DarkSiriumColor.sky2,
    green = DarkSiriumColor.green,
    sky4 = LightSiriumColor.sky4
)

val LocalSiriumColorScheme = staticCompositionLocalOf { LightSiriumColorScheme }
val LocalSiriumTypographyData = staticCompositionLocalOf { SiriumTypography }

@Composable
fun SiriumTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val siriumColorScheme = if (darkTheme) DarkSiriumColorScheme else LightSiriumColorScheme

    MaterialTheme(
        colorScheme = siriumColorScheme.material,
        typography = SiriumTypography.material,
        content = {
            CompositionLocalProvider(
                LocalSiriumColorScheme provides siriumColorScheme
            ) {
                content()
            }
        }
    )
}

val siriumColors: SiriumColorScheme
    @Composable
    get() = LocalSiriumColorScheme.current

val siriumTypography: STypography
    @Composable
    get() = LocalSiriumTypographyData.current