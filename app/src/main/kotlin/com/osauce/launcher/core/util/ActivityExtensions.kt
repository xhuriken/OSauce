package com.osauce.launcher.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent

/**
 * Extension to launch external activities without crashing.
 * 
 * Under the hood:
 * If an intent has no target (e.g., emulator without camera or dialer),
 * Android throws ActivityNotFoundException. Unhandled = app crash.
 * We catch it here and return false so UI can show a fallback if needed.
 */
fun Context.launchSafe(intent: Intent): Boolean {
    return try {
        startActivity(intent)
        true
    } catch (_: ActivityNotFoundException) {
        false
    } catch (_: SecurityException) {
        // OEM ROMs (like MIUI or HarmonyOS) sometimes block background launches
        false
    }
}
