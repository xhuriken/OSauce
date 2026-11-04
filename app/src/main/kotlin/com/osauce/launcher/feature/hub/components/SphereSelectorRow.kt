package com.osauce.launcher.feature.hub.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceIcons
import com.osauce.launcher.core.designsystem.OSauceTypography
import com.osauce.launcher.data.model.Sphere

/**
 * Horizontal selector for life spheres with clean vector iconography,
 * long-press to edit and plus button to create custom spheres.
 */
@Composable
fun SphereSelectorRow(
    spheres: List<Sphere>,
    selectedSphereId: String?,
    onSelectSphere: (String?) -> Unit,
    onAddSphere: () -> Unit,
    onEditSphere: (Sphere) -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "All" filter pill
        item(key = "all_spheres") {
            val isSelected = selectedSphereId == null
            SpherePill(
                label = "Tous",
                icon = null,
                accentColor = themeColors.primaryAccent,
                isSelected = isSelected,
                onClick = { onSelectSphere(null) }
            )
        }

        items(
            items = spheres,
            key = { it.id }
        ) { sphere ->
            val isSelected = selectedSphereId == sphere.id
            val parsedColor = runCatching {
                Color(android.graphics.Color.parseColor(sphere.colorHex))
            }.getOrDefault(themeColors.primaryAccent)

            SpherePill(
                label = sphere.name,
                icon = OSauceIcons.forSphere(sphere.icon),
                accentColor = parsedColor,
                isSelected = isSelected,
                onClick = { onSelectSphere(sphere.id) },
                onLongClick = { onEditSphere(sphere) }
            )
        }

        // Add Sphere button
        item(key = "add_sphere_btn") {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(themeColors.surface)
                    .border(1.dp, themeColors.border, CircleShape)
                    .clickable(onClick = onAddSphere),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = OSauceIcons.Add,
                    contentDescription = "Ajouter une sphère",
                    tint = themeColors.textPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SpherePill(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector?,
    accentColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null
) {
    val themeColors = LocalThemeColors.current
    val pillShape = RoundedCornerShape(18.dp)

    val bgColor by animateColorAsState(
        targetValue = if (isSelected) accentColor.copy(alpha = 0.20f) else themeColors.surface,
        animationSpec = tween(200),
        label = "pill_bg"
    )

    val borderColor by animateColorAsState(
        targetValue = if (isSelected) accentColor else themeColors.border,
        animationSpec = tween(200),
        label = "pill_border"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isSelected) accentColor else themeColors.textPrimary,
        animationSpec = tween(200),
        label = "pill_content"
    )

    Row(
        modifier = Modifier
            .clip(pillShape)
            .background(bgColor)
            .border(1.dp, borderColor, pillShape)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(16.dp)
            )
        }

        Text(
            text = label,
            style = OSauceTypography.TileTitle.copy(
                fontSize = 13.sp,
                color = contentColor
            )
        )
    }
}
