package com.osauce.launcher.feature.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.osauce.launcher.R
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceColors
import com.osauce.launcher.core.designsystem.OSauceTypography

/**
 * Top status bar with system insets, custom battery gauge, and settings trigger.
 */
@Composable
fun HomeHeader(
    dateText: String,
    batteryPercent: Int,
    isCharging: Boolean,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Date
        Text(
            text = dateText,
            style = OSauceTypography.FluidTicker.copy(color = themeColors.textSecondary)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Battery gauge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                BatteryIcon(
                    percent = batteryPercent,
                    isCharging = isCharging,
                    primaryColor = themeColors.primaryAccent,
                    borderColor = themeColors.border
                )

                Text(
                    text = stringResource(R.string.battery_status_format, batteryPercent),
                    style = OSauceTypography.FluidTicker.copy(color = themeColors.textSecondary)
                )
            }

            // Settings button
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = stringResource(R.string.cd_settings),
                tint = themeColors.textSecondary,
                modifier = Modifier
                    .size(22.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onOpenSettings
                    )
            )
        }
    }
}

/**
 * Minimal battery gauge with live fill level and terminal nub.
 */
@Composable
private fun BatteryIcon(
    percent: Int,
    isCharging: Boolean,
    primaryColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier
) {
    val fillColor = when {
        isCharging -> primaryColor
        percent <= 15 -> OSauceColors.AlertRed
        percent <= 25 -> OSauceColors.AlertAmber
        else -> primaryColor
    }

    Canvas(modifier = modifier.size(width = 22.dp, height = 11.dp)) {
        val w = size.width
        val h = size.height

        val strokeWidth = 1.3.dp.toPx()
        val corner = CornerRadius(2.5.dp.toPx(), 2.5.dp.toPx())

        // Outer battery shell
        val bodyWidth = w - 2.5.dp.toPx()
        drawRoundRect(
            color = borderColor,
            topLeft = Offset(0f, 0f),
            size = Size(bodyWidth, h),
            cornerRadius = corner,
            style = Stroke(width = strokeWidth)
        )

        // Terminal contact nub on right edge
        val nubHeight = h * 0.45f
        val nubWidth = 2.dp.toPx()
        val nubY = (h - nubHeight) / 2f
        drawRoundRect(
            color = borderColor,
            topLeft = Offset(bodyWidth, nubY),
            size = Size(nubWidth, nubHeight),
            cornerRadius = CornerRadius(1.dp.toPx(), 1.dp.toPx())
        )

        // Inner fill bar
        val padding = 2.2.dp.toPx()
        val maxFillWidth = bodyWidth - (padding * 2)
        val fillWidth = (maxFillWidth * (percent.coerceIn(0, 100) / 100f)).coerceAtLeast(1.5.dp.toPx())
        val fillHeight = h - (padding * 2)

        drawRoundRect(
            color = fillColor,
            topLeft = Offset(padding, padding),
            size = Size(fillWidth, fillHeight),
            cornerRadius = CornerRadius(1.5.dp.toPx(), 1.5.dp.toPx())
        )
    }
}
