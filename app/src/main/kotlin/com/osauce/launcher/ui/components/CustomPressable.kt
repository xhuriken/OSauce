package com.osauce.launcher.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Custom touchable button built 100% from scratch without Material Design.
 *
 * Why we created this:
 * 1. ZERO Material Ripple: Removes Google's default pink/purple splash animation under the finger.
 * 2. Tactile Spring Scale: Smoothly compresses down to 0.94x when pressed, then springs back up.
 * 3. Super Lightweight: Rendered directly on GPU without heavy Android theme layers.
 */
@Composable
fun CustomPressable(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    content: @Composable BoxScope.(isPressed: Boolean) -> Unit
) {
    // Tracks touch state (true when finger is down, false when lifted)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Smooth spring animation for physical feel
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.94f else 1.0f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "custom_press_scale"
    )

    // Slight fade out while pressed
    val alpha by animateFloatAsState(
        targetValue = if (isPressed) 0.70f else 1.0f,
        label = "custom_press_alpha"
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Disables default Android ripple
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        content(isPressed)
    }
}
