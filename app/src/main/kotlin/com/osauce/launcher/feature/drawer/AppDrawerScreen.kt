package com.osauce.launcher.feature.drawer

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.osauce.launcher.R
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceTypography
import com.osauce.launcher.data.model.AppItem

/**
 * Minimalist app drawer with instant search and desaturated monochrome icons.
 */
@Composable
fun AppDrawerScreen(
    apps: List<AppItem>,
    searchQuery: String,
    isLoading: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onLaunchApp: (AppItem) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current
    val monoFilter = remember {
        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        // Top search bar with Back icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
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

            // Search input field
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.drawer_search_hint),
                        color = themeColors.textSecondary,
                        style = OSauceTypography.TileTitle
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = themeColors.primaryAccent
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(
                        visible = searchQuery.isNotBlank(),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = null,
                                tint = themeColors.textSecondary
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = themeColors.surface,
                    unfocusedContainerColor = themeColors.surface,
                    focusedBorderColor = themeColors.primaryAccent,
                    unfocusedBorderColor = themeColors.border,
                    focusedTextColor = themeColors.textPrimary,
                    unfocusedTextColor = themeColors.textPrimary,
                    cursorColor = themeColors.primaryAccent
                ),
                modifier = Modifier.weight(1f)
            )
        }

        // Content area: loading, empty, or list
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = themeColors.primaryAccent)
            }
        } else if (apps.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.drawer_empty),
                    style = OSauceTypography.TileTitle.copy(color = themeColors.textSecondary)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = apps,
                    key = { it.id },
                    contentType = { "app_row" }
                ) { app ->
                    AppRowItem(
                        app = app,
                        monoFilter = monoFilter,
                        onClick = { onLaunchApp(app) }
                    )
                }
            }
        }
    }
}

@Composable
private fun AppRowItem(
    app: AppItem,
    monoFilter: ColorFilter,
    onClick: () -> Unit
) {
    val themeColors = LocalThemeColors.current
    val shape = RoundedCornerShape(14.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(themeColors.surface)
            .border(0.5.dp, themeColors.border, shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // App icon with monochrome filter
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(themeColors.elevated),
            contentAlignment = Alignment.Center
        ) {
            val bitmap = remember(app.icon) { app.icon?.toBitmap() }
            if (bitmap != null) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = app.label,
                    colorFilter = monoFilter,
                    modifier = Modifier.size(34.dp)
                )
            } else {
                Text(
                    text = app.label.take(1).uppercase(),
                    style = OSauceTypography.TileTitle.copy(color = themeColors.primaryAccent)
                )
            }
        }

        // App Label and Package details
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = app.label,
                style = OSauceTypography.TileTitle.copy(color = themeColors.textPrimary)
            )
            Text(
                text = app.packageName,
                style = OSauceTypography.SecondsClock.copy(
                    fontSize = androidx.compose.ui.unit.TextUnit(
                        11f,
                        androidx.compose.ui.unit.TextUnitType.Sp
                    ),
                    color = themeColors.textSecondary
                ),
                maxLines = 1
            )
        }
    }
}

private fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable && bitmap != null) {
        return bitmap
    }
    val width = intrinsicWidth.coerceAtLeast(1)
    val height = intrinsicHeight.coerceAtLeast(1)
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmp)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bmp
}

