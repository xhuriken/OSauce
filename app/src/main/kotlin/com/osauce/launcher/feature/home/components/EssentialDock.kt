package com.osauce.launcher.feature.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.osauce.launcher.R
import com.osauce.launcher.core.designsystem.LocalThemeColors

/**
 * Tactile dock for essential actions.
 * Padded safely above Android navigation bar.
 */
@Composable
fun EssentialDock(
    onDialer: () -> Unit,
    onMessages: () -> Unit,
    onCamera: () -> Unit,
    onOpenApps: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DockIconButton(
            icon = Icons.Rounded.Call,
            contentDescription = stringResource(R.string.cd_phone),
            onClick = onDialer
        )
        DockIconButton(
            icon = Icons.Rounded.ChatBubble,
            contentDescription = stringResource(R.string.cd_messages),
            onClick = onMessages
        )
        DockIconButton(
            icon = Icons.Rounded.PhotoCamera,
            contentDescription = stringResource(R.string.cd_camera),
            onClick = onCamera
        )
        DockIconButton(
            icon = Icons.Rounded.GridView,
            contentDescription = stringResource(R.string.cd_apps),
            onClick = onOpenApps
        )
    }
}

@Composable
private fun DockIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current
    val shape = RoundedCornerShape(18.dp)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1.0f,
        animationSpec = tween(durationMillis = 100),
        label = "dock_scale"
    )

    Box(
        modifier = modifier
            .size(62.dp)
            .scale(scale)
            .clip(shape)
            .background(if (isPressed) themeColors.elevated else themeColors.surface)
            .border(1.dp, themeColors.border, shape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (isPressed) themeColors.primaryAccent else themeColors.textPrimary,
            modifier = Modifier.size(26.dp)
        )
    }
}
