package com.osauce.launcher.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.osauce.launcher.R
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceColors
import com.osauce.launcher.core.designsystem.OSauceTypography
import com.osauce.launcher.feature.home.components.EssentialDock
import com.osauce.launcher.feature.home.components.HomeHeader

/**
 * Main Home Screen ("The Sauce").
 * Rendered transparently over the root FluidBackground canvas.
 */
@Composable
fun HomeScreen(
    state: HomeUiState,
    calmMessage: String,
    onToggleSeconds: () -> Unit,
    onDialer: () -> Unit,
    onMessages: () -> Unit,
    onCamera: () -> Unit,
    onOpenApps: () -> Unit,
    onOpenDigest: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- 1. Top Status Bar ---
        HomeHeader(
            dateText = state.date,
            batteryPercent = state.batteryPercent,
            isCharging = state.isCharging,
            onOpenSettings = onOpenSettings
        )

        // --- 2. Center: Clock & Calm Motto ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Monument Clock with sliding animated seconds
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onToggleSeconds
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = state.time,
                    style = OSauceTypography.FluidClock.copy(color = themeColors.textPrimary)
                )

                AnimatedVisibility(
                    visible = state.showSeconds,
                    enter = fadeIn() + expandHorizontally(),
                    exit = fadeOut() + shrinkHorizontally()
                ) {
                    Text(
                        text = ":${state.seconds}",
                        style = OSauceTypography.SecondsClock.copy(color = themeColors.primaryAccent),
                        modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
                    )
                }
            }

            // Calm Status: Organic wave glyph, zero AI bullet points
            val isPending = state.heldNotificationsCount > 0
            val statusText = if (isPending) {
                stringResource(R.string.digest_held, state.heldNotificationsCount)
            } else {
                calmMessage.ifBlank { stringResource(R.string.default_calm_message) }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onOpenDigest
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "≋",
                    style = OSauceTypography.FluidTicker.copy(
                        color = if (isPending) OSauceColors.AlertAmber else themeColors.primaryAccent
                    )
                )

                Text(
                    text = statusText,
                    style = OSauceTypography.FluidTicker.copy(
                        color = if (isPending) themeColors.textPrimary else themeColors.textSecondary
                    )
                )
            }
        }

        // --- 3. Bottom: Iconic Dock (Safe above navigation bar) ---
        EssentialDock(
            onDialer = onDialer,
            onMessages = onMessages,
            onCamera = onCamera,
            onOpenApps = onOpenApps
        )
    }
}
