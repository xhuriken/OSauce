package com.osauce.launcher.feature.hub.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.R
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceIcons
import com.osauce.launcher.core.designsystem.OSauceTypography

/**
 * Top zero-friction input bar to add new thoughts or tasks.
 */
@Composable
fun QuickCaptureBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current
    val barShape = RoundedCornerShape(16.dp)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(barShape)
            .background(themeColors.surface)
            .border(1.dp, themeColors.border, barShape)
            .padding(horizontal = 14.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text(
                    text = "Ajouter une intention ou tâche...",
                    style = OSauceTypography.TileTitle.copy(
                        fontSize = 13.sp,
                        color = themeColors.textSecondary
                    )
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { onSubmit() }),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                unfocusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                focusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
                unfocusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
                focusedTextColor = themeColors.textPrimary,
                unfocusedTextColor = themeColors.textPrimary,
                cursorColor = themeColors.primaryAccent
            ),
            textStyle = OSauceTypography.TileTitle.copy(fontSize = 14.sp),
            modifier = Modifier.weight(1f)
        )

        // Submit action button
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(
                    if (query.isNotBlank()) themeColors.primaryAccent else themeColors.border.copy(alpha = 0.5f)
                )
                .clickable(enabled = query.isNotBlank(), onClick = onSubmit),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = OSauceIcons.Add,
                contentDescription = null,
                tint = if (query.isNotBlank()) themeColors.background else themeColors.textSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
