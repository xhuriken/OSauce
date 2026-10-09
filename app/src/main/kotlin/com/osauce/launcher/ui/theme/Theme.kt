package com.osauce.launcher.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = SageGreen,
    secondary = SlateBlue,
    tertiary = SoftAmber,
    background = OledBlack,
    surface = CardDark,
    onPrimary = OledBlack,
    onSecondary = OledBlack,
    onTertiary = OledBlack,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun OSauceTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
