package com.osauce.launcher.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// =============================================================================
// OpenSauce Design Tokens (OLED Black & Calm Nord Accents)
// =============================================================================

val OledBlack = Color(0xFF000000)
val CardDark = Color(0xFF121212)
val CardDarkVariant = Color(0xFF1C1C1E)
val BorderDark = Color(0xFF2C2C2E)

val TextPrimary = Color(0xFFF5F5F7)
val TextSecondary = Color(0xFF8E8E93)
val TextMuted = Color(0xFF636366)

val SageGreen = Color(0xFF88C0D0)
val SoftAmber = Color(0xFFEBCB8B)
val SlateBlue = Color(0xFF81A1C1)

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

/**
 * Main theme wrapper for OpenSauce.
 * Applies dark OLED colors and typographic defaults.
 */
@Composable
fun OSauceTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
