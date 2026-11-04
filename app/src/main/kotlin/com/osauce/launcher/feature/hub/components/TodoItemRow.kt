package com.osauce.launcher.feature.hub.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceIcons
import com.osauce.launcher.core.designsystem.OSauceTypography
import com.osauce.launcher.data.model.TodoItem

/**
 * Interactive row displaying a task with smooth spring checkbox and tactile feedback.
 */
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

    val checkScale by animateFloatAsState(
        targetValue = if (todo.isCompleted) 1.0f else 0.85f,
        animationSpec = spring(dampingRatio = 0.8f),
        label = "check_scale"
    )

    val checkBgColor by animateColorAsState(
        targetValue = if (todo.isCompleted) accentColor else Color.Transparent,
        label = "check_bg"
    )

    val checkBorderColor by animateColorAsState(
        targetValue = if (todo.isCompleted) accentColor else themeColors.border,
        label = "check_border"
    )

    Row(
        modifier = modifier
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
            if (todo.isCompleted) {
                Icon(
                    imageVector = OSauceIcons.Check,
                    contentDescription = null,
                    tint = themeColors.background,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        // Title and notes
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = todo.title,
                style = OSauceTypography.TileTitle.copy(
                    fontSize = 14.sp,
                    color = if (todo.isCompleted) themeColors.textSecondary else themeColors.textPrimary,
                    textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
            )

            if (!todo.notes.isNullOrBlank()) {
                Text(
                    text = todo.notes,
                    style = OSauceTypography.SecondsClock.copy(
                        fontSize = 11.sp,
                        color = themeColors.textSecondary
                    ),
                    maxLines = 1
                )
            }
        }

        // Delete button
        IconButton(
            onClick = onDelete,
            modifier = Modifier.size(28.dp)
        ) {
            Icon(
                imageVector = OSauceIcons.Close,
                contentDescription = null,
                tint = themeColors.textSecondary.copy(alpha = 0.5f),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
