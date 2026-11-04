package com.osauce.launcher.data.repository

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.osauce.launcher.data.model.AppItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository to query installed applications from Android PackageManager.
 */
class AppRepository(private val context: Context) {

    suspend fun getInstalledApps(): List<AppItem> = withContext(Dispatchers.IO) {
        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfos = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        val selfPackage = context.packageName

        resolveInfos.mapNotNull { info ->
            val pkg = info.activityInfo.packageName
            if (pkg == selfPackage) return@mapNotNull null

            val name = info.activityInfo.name
            val label = info.loadLabel(pm).toString().trim()
            val icon = try {
                info.loadIcon(pm)
            } catch (_: Exception) {
                null
            }

            AppItem(
                id = "$pkg/$name",
                label = label.ifBlank { pkg },
                packageName = pkg,
                activityName = name,
                icon = icon
            )
        }.sortedBy { it.label.lowercase() }
    }
}

