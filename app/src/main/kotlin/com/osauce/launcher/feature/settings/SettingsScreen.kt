package com.osauce.launcher.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.GridOn
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.osauce.launcher.R
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceTypography
import com.osauce.launcher.core.designsystem.ThemeMod

/**
 * Settings screen rendered over the root fluid background canvas.
 */
@Composable
fun SettingsScreen(
    showSeconds: Boolean,
    calmMessage: String,
    bgVibrancy: String,
    cellShape: String,
    gridDensity: Int,
    touchParticles: Boolean,
    dragParticles: Boolean,
    activeTheme: ThemeMod,
    availableThemes: List<ThemeMod>,
    onSelectTheme: (ThemeMod) -> Unit,
    onImportTheme: (String) -> Unit,
    onToggleSeconds: () -> Unit,
    onSaveCalmMessage: (String) -> Unit,
    onChangeVibrancy: (String) -> Unit,
    onChangeCellShape: (String) -> Unit,
    onChangeGridDensity: (Int) -> Unit,
    onToggleTouchParticles: (Boolean) -> Unit,
    onToggleDragParticles: (Boolean) -> Unit,
    onOpenDefaultHomeSettings: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current
    var isEditingCalmText by remember { mutableStateOf(false) }
    var calmInput by remember { mutableStateOf(calmMessage) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Top header with back icon
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(themeColors.surface)
                    .border(1.dp, themeColors.border, CircleShape)
                    .clickable(onClick = onBack),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.action_close),
                    tint = themeColors.textPrimary
                )
            }

            Text(
                text = stringResource(R.string.settings_title),
                style = OSauceTypography.TileTitle.copy(color = themeColors.textPrimary)
            )
        }

        // Section 1: Default launcher selector
        SettingsTile(
            title = stringResource(R.string.settings_launcher_title),
            subtitle = stringResource(R.string.settings_launcher_desc),
            icon = Icons.Rounded.Home,
            onClick = onOpenDefaultHomeSettings
        )

        // Section 2: Visual Themes & Palettes (Level 1 Modding)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(themeColors.surface)
                .border(1.dp, themeColors.border, RoundedCornerShape(16.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ColorLens,
                    contentDescription = null,
                    tint = themeColors.primaryAccent
                )
                Column {
                    Text(
                        text = stringResource(R.string.settings_theme_title),
                        style = OSauceTypography.TileTitle.copy(color = themeColors.textPrimary)
                    )
                    Text(
                        text = stringResource(R.string.settings_theme_desc),
                        style = OSauceTypography.FluidTicker.copy(color = themeColors.textSecondary)
                    )
                }
            }

            // Theme Cards List
            availableThemes.forEach { theme ->
                val isSelected = theme.id == activeTheme.id
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) themeColors.elevated else themeColors.background)
                        .border(
                            width = if (isSelected) 1.5.dp else 0.5.dp,
                            color = if (isSelected) themeColors.primaryAccent else themeColors.border,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onSelectTheme(theme) }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = theme.name,
                                style = OSauceTypography.TileTag.copy(color = themeColors.textPrimary)
                            )
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = null,
                                    tint = themeColors.primaryAccent,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        Text(
                            text = theme.description,
                            style = OSauceTypography.SecondsClock.copy(
                                fontSize = androidx.compose.ui.unit.TextUnit(
                                    11f,
                                    androidx.compose.ui.unit.TextUnitType.Sp
                                ),
                                color = themeColors.textSecondary
                            )
                        )
                    }

                    // Color Preview Circles
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(theme.colors.background)
                                .border(1.dp, themeColors.border, CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(theme.colors.primaryAccent)
                        )
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(theme.colors.secondaryAccent)
                        )
                    }
                }
            }
        }

        // Section 3: Matrix Fine-Tuning ("The Sauce" Grid controls)
        val shape = RoundedCornerShape(16.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(themeColors.surface)
                .border(1.dp, themeColors.border, shape)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.AutoAwesome,
                    contentDescription = null,
                    tint = themeColors.primaryAccent
                )
                Column {
                    Text(
                        text = stringResource(R.string.settings_bg_title),
                        style = OSauceTypography.TileTitle.copy(color = themeColors.textPrimary)
                    )
                    Text(
                        text = stringResource(R.string.settings_bg_vibrancy_desc),
                        style = OSauceTypography.FluidTicker.copy(color = themeColors.textSecondary)
                    )
                }
            }

            // Grid Cell Density selector: Large (10), Normal (15), Dense (20), Micro (25)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.settings_bg_density_title),
                    style = OSauceTypography.TileTag.copy(color = themeColors.textPrimary)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val densities = listOf(
                        10 to stringResource(R.string.density_coarse),
                        15 to stringResource(R.string.density_normal),
                        20 to stringResource(R.string.density_dense),
                        25 to stringResource(R.string.density_micro)
                    )
                    densities.forEach { (d, label) ->
                        val isSelected = gridDensity == d
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isSelected) themeColors.primaryAccent else themeColors.elevated)
                                .clickable { onChangeGridDensity(d) }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                style = OSauceTypography.TileTag.copy(
                                    color = if (isSelected) themeColors.background else themeColors.textSecondary
                                )
                            )
                        }
                    }
                }
            }

            // Cellular shape selection
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.settings_bg_shape_title),
                    style = OSauceTypography.TileTag.copy(color = themeColors.textPrimary)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val shapes = listOf(
                        "ROUNDED_SQUARE" to stringResource(R.string.shape_squares),
                        "ORGANIC_CELL" to stringResource(R.string.shape_cells),
                        "CROSS_MATRIX" to stringResource(R.string.shape_cross)
                    )
                    shapes.forEach { (key, label) ->
                        val isSelected = cellShape.equals(key, ignoreCase = true)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isSelected) themeColors.primaryAccent else themeColors.elevated)
                                .clickable { onChangeCellShape(key) }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                style = OSauceTypography.TileTag.copy(
                                    color = if (isSelected) themeColors.background else themeColors.textSecondary
                                )
                            )
                        }
                    }
                }
            }

            // Vibrancy selection: Subtle, Balanced, Vibrant
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.settings_bg_vibrancy_title),
                    style = OSauceTypography.TileTag.copy(color = themeColors.textPrimary)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val vibrancies = listOf(
                        "CALM" to stringResource(R.string.vibrancy_calm),
                        "BALANCED" to stringResource(R.string.vibrancy_balanced),
                        "VIBRANT" to stringResource(R.string.vibrancy_vibrant)
                    )
                    vibrancies.forEach { (key, label) ->
                        val isSelected = bgVibrancy.equals(key, ignoreCase = true)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isSelected) themeColors.primaryAccent else themeColors.elevated)
                                .clickable { onChangeVibrancy(key) }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                style = OSauceTypography.TileTag.copy(
                                    color = if (isSelected) themeColors.background else themeColors.textSecondary
                                )
                            )
                        }
                    }
                }
            }

            // Reactive touch toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.settings_bg_touch_title),
                        style = OSauceTypography.TileTag.copy(color = themeColors.textPrimary)
                    )
                    Text(
                        text = stringResource(R.string.settings_bg_touch_desc),
                        style = OSauceTypography.SecondsClock.copy(
                            fontSize = androidx.compose.ui.unit.TextUnit(
                                11f,
                                androidx.compose.ui.unit.TextUnitType.Sp
                            ),
                            color = themeColors.textSecondary
                        )
                    )
                }
                Switch(
                    checked = touchParticles,
                    onCheckedChange = onToggleTouchParticles,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = themeColors.background,
                        checkedTrackColor = themeColors.primaryAccent,
                        checkedBorderColor = themeColors.primaryAccent,
                        uncheckedThumbColor = themeColors.textSecondary,
                        uncheckedTrackColor = themeColors.elevated,
                        uncheckedBorderColor = themeColors.border
                    )
                )
            }

            // Drag particle trail toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.settings_bg_drag_title),
                        style = OSauceTypography.TileTag.copy(color = themeColors.textPrimary)
                    )
                    Text(
                        text = stringResource(R.string.settings_bg_drag_desc),
                        style = OSauceTypography.SecondsClock.copy(
                            fontSize = androidx.compose.ui.unit.TextUnit(
                                11f,
                                androidx.compose.ui.unit.TextUnitType.Sp
                            ),
                            color = themeColors.textSecondary
                        )
                    )
                }
                Switch(
                    checked = dragParticles,
                    onCheckedChange = onToggleDragParticles,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = themeColors.background,
                        checkedTrackColor = themeColors.primaryAccent,
                        checkedBorderColor = themeColors.primaryAccent,
                        uncheckedThumbColor = themeColors.textSecondary,
                        uncheckedTrackColor = themeColors.elevated,
                        uncheckedBorderColor = themeColors.border
                    )
                )
            }
        }

        // Section 4: Seconds display toggle
        SettingsToggleTile(
            title = stringResource(R.string.settings_seconds_title),
            subtitle = stringResource(R.string.settings_seconds_desc),
            icon = Icons.Rounded.Schedule,
            checked = showSeconds,
            onCheckedChange = { onToggleSeconds() }
        )

        // Section 5: Calm Motto
        val defaultMotto = stringResource(R.string.default_calm_message)
        val currentMotto = calmMessage.ifBlank { defaultMotto }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(themeColors.surface)
                .border(1.dp, themeColors.border, shape)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.settings_calm_text_title),
                        style = OSauceTypography.TileTitle.copy(color = themeColors.textPrimary)
                    )
                    Text(
                        text = stringResource(R.string.settings_calm_text_desc),
                        style = OSauceTypography.FluidTicker.copy(color = themeColors.textSecondary)
                    )
                }

                IconButton(onClick = { isEditingCalmText = !isEditingCalmText }) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = stringResource(R.string.settings_calm_text_title),
                        tint = themeColors.primaryAccent
                    )
                }
            }

            if (isEditingCalmText) {
                OutlinedTextField(
                    value = calmInput,
                    onValueChange = { calmInput = it },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.settings_calm_text_hint),
                            color = themeColors.textSecondary
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = themeColors.primaryAccent,
                        unfocusedBorderColor = themeColors.border,
                        focusedTextColor = themeColors.textPrimary,
                        unfocusedTextColor = themeColors.textPrimary,
                        cursorColor = themeColors.primaryAccent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = {
                        calmInput = calmMessage
                        isEditingCalmText = false
                    }) {
                        Text(
                            text = stringResource(R.string.action_close),
                            color = themeColors.textSecondary
                        )
                    }

                    Button(
                        onClick = {
                            onSaveCalmMessage(calmInput.trim())
                            isEditingCalmText = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = themeColors.primaryAccent)
                    ) {
                        Text(
                            text = stringResource(R.string.action_save),
                            color = themeColors.background
                        )
                    }
                }
            } else {
                Text(
                    text = "« $currentMotto »",
                    style = OSauceTypography.FluidTicker.copy(color = themeColors.primaryAccent)
                )
            }
        }

        // Section 6: App Info
        SettingsTile(
            title = stringResource(R.string.settings_about_title),
            subtitle = stringResource(R.string.settings_about_desc),
            icon = Icons.Rounded.AutoAwesome,
            onClick = {}
        )
    }
}

@Composable
private fun SettingsTile(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    val themeColors = LocalThemeColors.current
    val shape = RoundedCornerShape(16.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(themeColors.surface)
            .border(1.dp, themeColors.border, shape)
            .clickable(onClick = onClick)
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(icon, contentDescription = null, tint = themeColors.primaryAccent)
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = OSauceTypography.TileTitle.copy(color = themeColors.textPrimary))
            Text(text = subtitle, style = OSauceTypography.FluidTicker.copy(color = themeColors.textSecondary))
        }
    }
}

@Composable
private fun SettingsToggleTile(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val themeColors = LocalThemeColors.current
    val shape = RoundedCornerShape(16.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(themeColors.surface)
            .border(1.dp, themeColors.border, shape)
            .clickable { onCheckedChange(!checked) }
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(icon, contentDescription = null, tint = themeColors.primaryAccent)
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = OSauceTypography.TileTitle.copy(color = themeColors.textPrimary))
            Text(text = subtitle, style = OSauceTypography.FluidTicker.copy(color = themeColors.textSecondary))
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = themeColors.background,
                checkedTrackColor = themeColors.primaryAccent,
                checkedBorderColor = themeColors.primaryAccent,
                uncheckedThumbColor = themeColors.textSecondary,
                uncheckedTrackColor = themeColors.elevated,
                uncheckedBorderColor = themeColors.border
            )
        )
    }
}
