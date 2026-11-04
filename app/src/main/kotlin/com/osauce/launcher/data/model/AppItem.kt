package com.osauce.launcher.data.model

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Immutable

/**
 * Immutable model representing an installed Android app.
 */
@Immutable
data class AppItem(
    val id: String,
    val label: String,
    val packageName: String,
    val activityName: String,
    val icon: Drawable? = null
)

