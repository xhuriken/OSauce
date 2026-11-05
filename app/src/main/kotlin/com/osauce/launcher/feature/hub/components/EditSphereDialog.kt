package com.osauce.launcher.feature.hub.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceIcons
import com.osauce.launcher.core.designsystem.OSauceTypography
import com.osauce.launcher.data.model.Sphere
import com.osauce.launcher.data.model.SphereIcon

/**
 * Minimalist dialog for creating or editing a life sphere.
 * Pure geometric design adhering to OSauce guidelines (zero emoji).
 */
@Composable
fun EditSphereDialog(
    sphere: Sphere?,
    onDismiss: () -> Unit,
    onSave: (name: String, icon: SphereIcon, colorHex: String) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    val themeColors = LocalThemeColors.current

    val palette = remember {
        listOf(
            "#60A5FA", // Blue
            "#4ADE80", // Green
            "#FBBF24", // Amber
            "#A78BFA", // Purple
            "#F43F5E", // Rose
            "#2DD4BF", // Teal
            "#FB923C", // Orange
            "#E879F9"  // Fuchsia
        )
    }

    val iconOptions = remember {
        listOf(
            SphereIcon.USERS,
            SphereIcon.BRIEFCASE,
            SphereIcon.PULSE,
            SphereIcon.LEAF,
            SphereIcon.STAR,
            SphereIcon.SHIELD,
            SphereIcon.TERMINAL,
            SphereIcon.COMPASS
        )
    }

    var name by remember { mutableStateOf(sphere?.name ?: "") }
    var selectedIcon by remember { mutableStateOf(sphere?.icon ?: SphereIcon.STAR) }
    var selectedColorHex by remember { mutableStateOf(sphere?.colorHex ?: palette.first()) }

    val activeColor = remember(selectedColorHex) {
        runCatching { Color(android.graphics.Color.parseColor(selectedColorHex)) }
            .getOrDefault(themeColors.primaryAccent)
    }

    Dialog(onDismissRequest = onDismiss) {
        val dialogShape = RoundedCornerShape(20.dp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(dialogShape)
                .background(themeColors.surface)
                .border(1.dp, themeColors.border, dialogShape)
                .padding(20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (sphere == null) "Nouvelle Sphère" else "Modifier la Sphère",
                    style = OSauceTypography.TileTitle.copy(
                        fontSize = 16.sp,
                        color = themeColors.textPrimary
                    )
                )

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = OSauceIcons.Close,
                        contentDescription = null,
                        tint = themeColors.textSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Name label and input
            Text(
                text = "NOM DE LA SPHÈRE",
                style = OSauceTypography.SecondsClock.copy(
                    fontSize = 11.sp,
                    color = themeColors.textSecondary,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            val inputShape = RoundedCornerShape(12.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(inputShape)
                    .background(themeColors.background)
                    .border(1.dp, themeColors.border, inputShape)
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                if (name.isEmpty()) {
                    Text(
                        text = "Ex: Finance, Studio, Famille...",
                        style = OSauceTypography.TileTitle.copy(
                            fontSize = 14.sp,
                            color = themeColors.textSecondary.copy(alpha = 0.5f)
                        )
                    )
                }

                BasicTextField(
                    value = name,
                    onValueChange = { name = it },
                    textStyle = OSauceTypography.TileTitle.copy(
                        fontSize = 14.sp,
                        color = themeColors.textPrimary
                    ),
                    cursorBrush = SolidColor(activeColor),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Color Palette Selector
            Text(
                text = "COULEUR D'ACCENT",
                style = OSauceTypography.SecondsClock.copy(
                    fontSize = 11.sp,
                    color = themeColors.textSecondary,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                palette.forEach { hex ->
                    val color = runCatching { Color(android.graphics.Color.parseColor(hex)) }
                        .getOrDefault(themeColors.primaryAccent)
                    val isSelected = selectedColorHex.equals(hex, ignoreCase = true)

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = if (isSelected) 2.5.dp else 0.dp,
                                color = if (isSelected) themeColors.textPrimary else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { selectedColorHex = hex }
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Icon Selector
            Text(
                text = "SYMBOLE GÉOMÉTRIQUE",
                style = OSauceTypography.SecondsClock.copy(
                    fontSize = 11.sp,
                    color = themeColors.textSecondary,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                iconOptions.forEach { icon ->
                    val isSelected = selectedIcon == icon
                    val iconVector = OSauceIcons.forSphere(icon)

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) activeColor.copy(alpha = 0.2f) else themeColors.background)
                            .border(
                                width = 1.dp,
                                color = if (isSelected) activeColor else themeColors.border,
                                shape = CircleShape
                            )
                            .clickable { selectedIcon = icon },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = iconVector,
                            contentDescription = null,
                            tint = if (isSelected) activeColor else themeColors.textSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (sphere != null && !sphere.isDefault && onDelete != null) {
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = OSauceIcons.Delete,
                            contentDescription = "Supprimer",
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Cancel
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable(onClick = onDismiss)
                            .padding(horizontal = 14.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "Annuler",
                            style = OSauceTypography.TileTitle.copy(
                                fontSize = 13.sp,
                                color = themeColors.textSecondary
                            )
                        )
                    }

                    // Save
                    val canSave = name.trim().isNotBlank()
                    val saveShape = RoundedCornerShape(12.dp)
                    Box(
                        modifier = Modifier
                            .clip(saveShape)
                            .background(if (canSave) activeColor else themeColors.border)
                            .clickable(enabled = canSave) {
                                onSave(name.trim(), selectedIcon, selectedColorHex)
                            }
                            .padding(horizontal = 18.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = if (sphere == null) "Créer" else "Enregistrer",
                            style = OSauceTypography.TileTitle.copy(
                                fontSize = 13.sp,
                                color = themeColors.background
                            )
                        )
                    }
                }
            }
        }
    }
}

