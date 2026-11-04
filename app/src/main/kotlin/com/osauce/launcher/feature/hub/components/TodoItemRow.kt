package com.osauce.launcher.feature.hub.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceIcons
import com.osauce.launcher.core.designsystem.OSauceTypography
import com.osauce.launcher.data.model.TodoItem

/**
 * Interactive row displaying a task with swipe actions, tactile spring checkbox,
 * and animated drawn strikethrough.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemRow(
    todo: TodoItem,
    accentColor: Color,
    onToggle: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current
    val rowShape = RoundedCornerShape(14.dp)

    // Swipe to dismiss / toggle state
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            when (value) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onToggle()
                    false // Don't remove item, rebound back after toggling
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    onDelete()
                    true // Remove item
                }
                SwipeToDismissBoxValue.Settled -> false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier
            .fillMaxWidth()
            .clip(rowShape),
        backgroundContent = {
            val direction = dismissState.dismissDirection
            val isCompleting = direction == SwipeToDismissBoxValue.StartToEnd
            val bgColor by animateColorAsState(
                targetValue = when (direction) {
                    SwipeToDismissBoxValue.StartToEnd -> accentColor.copy(alpha = 0.18f)
                    SwipeToDismissBoxValue.EndToStart -> Color(0xFFEF4444).copy(alpha = 0.18f)
                    else -> Color.Transparent
                },
                label = "swipe_bg"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(bgColor)
                    .padding(horizontal = 20.dp),
                contentAlignment = if (isCompleting) Alignment.CenterStart else Alignment.CenterEnd
            ) {
                if (isCompleting) {
                    Icon(
                        imageVector = OSauceIcons.Check,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(20.dp)
                    )
                } else if (direction == SwipeToDismissBoxValue.EndToStart) {
                    Icon(
                        imageVector = OSauceIcons.Delete,
                        contentDescription = null,
                        tint = Color(0xFFEF4444),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    ) {
        // Bouncy spring checkbox scale
        val checkScale by animateFloatAsState(
            targetValue = if (todo.isCompleted) 1.0f else 0.88f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMediumLow
            ),
            label = "check_scale"
        )

        val checkBgColor by animateColorAsState(
            targetValue = if (todo.isCompleted) accentColor else Color.Transparent,
            animationSpec = spring(stiffness = Spring.StiffnessMedium),
            label = "check_bg"
        )

        val checkBorderColor by animateColorAsState(
            targetValue = if (todo.isCompleted) accentColor else themeColors.border,
            animationSpec = spring(stiffness = Spring.StiffnessMedium),
            label = "check_border"
        )

        // Animated strike line drawing (0f -> 1f)
        val strikeProgress by animateFloatAsState(
            targetValue = if (todo.isCompleted) 1f else 0f,
            animationSpec = spring(
                dampingRatio = 0.8f,
                stiffness = Spring.StiffnessMediumLow
            ),
            label = "strike_progress"
        )

        // Animated text color fade
        val textColor by animateColorAsState(
            targetValue = if (todo.isCompleted) themeColors.textSecondary.copy(alpha = 0.55f) else themeColors.textPrimary,
            animationSpec = spring(stiffness = Spring.StiffnessMedium),
            label = "text_color"
        )

        val checkmarkScale by animateFloatAsState(
            targetValue = if (todo.isCompleted) 1f else 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            ),
            label = "checkmark_scale"
        )

        val checkmarkAlpha by animateFloatAsState(
            targetValue = if (todo.isCompleted) 1f else 0f,
            animationSpec = spring(stiffness = Spring.StiffnessMedium),
            label = "checkmark_alpha"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(rowShape)
                .background(themeColors.surface)
                .border(0.5.dp, themeColors.border, rowShape)
                .clickable(onClick = onToggle)
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Tactile checkbox
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .scale(checkScale)
                    .clip(CircleShape)
                    .background(checkBgColor)
                    .border(1.5.dp, checkBorderColor, CircleShape)
                    .clickable(onClick = onToggle),
                contentAlignment = Alignment.Center
            ) {
                if (checkmarkAlpha > 0.01f) {
                    Icon(
                        imageVector = OSauceIcons.Check,
                        contentDescription = null,
                        tint = themeColors.background,
                        modifier = Modifier
                            .size(13.dp)
                            .scale(checkmarkScale)
                    )
                }
            }

            // Title with animated dynamic strikethrough drawing
            Column(
                modifier = Modifier
                    .weight(1f)
                    .drawWithContent {
                        drawContent()
                        if (strikeProgress > 0.01f) {
                            val strokeWidth = 1.5.dp.toPx()
                            val lineY = size.height / 2f
                            drawLine(
                                color = themeColors.textSecondary.copy(alpha = 0.7f),
                                start = Offset(0f, lineY),
                                end = Offset(size.width * strikeProgress, lineY),
                                strokeWidth = strokeWidth,
                                cap = StrokeCap.Round
                            )
                        }
                    }
            ) {
                Text(
                    text = todo.title,
                    style = OSauceTypography.TileTitle.copy(
                        fontSize = 14.sp,
                        color = textColor
                    )
                )

                if (!todo.notes.isNullOrBlank()) {
                    Text(
                        text = todo.notes,
                        style = OSauceTypography.SecondsClock.copy(
                            fontSize = 11.sp,
                            color = themeColors.textSecondary.copy(alpha = if (todo.isCompleted) 0.4f else 0.7f)
                        ),
                        maxLines = 1
                    )
                }
            }

            // Quick delete action
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = OSauceIcons.Close,
                    contentDescription = null,
                    tint = themeColors.textSecondary.copy(alpha = 0.4f),
                    modifier = Modifier.size(15.dp)
                )
            }
        }
    }
}
