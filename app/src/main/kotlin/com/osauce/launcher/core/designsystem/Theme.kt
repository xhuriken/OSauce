package com.osauce.launcher.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalThemeMod = staticCompositionLocalOf { ThemeMod.Presets[0] }
val LocalThemeColors = staticCompositionLocalOf { ThemeModColors.Default }

@Composable
fun OSauceTheme(
    themeMod: ThemeMod = ThemeMod.Presets[0],
    content: @Composable () -> Unit
) {
    val dynamicScheme = darkColorScheme(
        background = themeMod.colors.background,
        surface = themeMod.colors.surface,
        surfaceVariant = themeMod.colors.elevated,
        outline = themeMod.colors.border,
        primary = themeMod.colors.primaryAccent,
        secondary = themeMod.colors.secondaryAccent,
        onBackground = themeMod.colors.textPrimary,
        onSurface = themeMod.colors.textPrimary,
        onPrimary = themeMod.colors.background
    )

    CompositionLocalProvider(
        LocalThemeMod provides themeMod,
        LocalThemeColors provides themeMod.colors
    ) {
        MaterialTheme(
            colorScheme = dynamicScheme,
            content = content
        )
    }
}
