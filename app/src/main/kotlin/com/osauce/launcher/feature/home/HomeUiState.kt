package com.osauce.launcher.feature.home

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Immutable UI state for the Home dashboard.
 */
data class HomeUiState(
    val time: String,
    val seconds: String,
    val date: String,
    val batteryPercent: Int,
    val isCharging: Boolean,
    val heldNotificationsCount: Int = 0,
    val showSeconds: Boolean = false
)

/**
 * State provider for HomeScreen.
 */
@Composable
fun rememberHomeState(
    showSeconds: Boolean = false,
    heldNotificationsCount: Int = 0
): HomeUiState {
    val context = LocalContext.current
    var currentTime by remember { mutableStateOf(LocalTime.now()) }
    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(showSeconds) {
        while (true) {
            val now = LocalTime.now()
            currentTime = now
            currentDate = LocalDate.now()

            if (showSeconds) {
                delay(1000L)
            } else {
                val millisToNextMinute = ((60 - now.second) * 1000L) - (now.nano / 1_000_000L)
                delay(millisToNextMinute.coerceAtLeast(1000L))
            }
        }
    }

    val batteryInfo = remember(currentTime.minute) {
        readBatteryStatus(context)
    }

    val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm") }
    val secondsFormatter = remember { DateTimeFormatter.ofPattern("ss") }
    val dateFormatter = remember { 
        DateTimeFormatter.ofPattern("EEE dd MMM", Locale.getDefault()) 
    }

    return HomeUiState(
        time = currentTime.format(timeFormatter),
        seconds = currentTime.format(secondsFormatter),
        date = currentDate.format(dateFormatter).uppercase(),
        batteryPercent = batteryInfo.first,
        isCharging = batteryInfo.second,
        heldNotificationsCount = heldNotificationsCount,
        showSeconds = showSeconds
    )
}

private fun readBatteryStatus(context: Context): Pair<Int, Boolean> {
    return try {
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = context.registerReceiver(null, intentFilter)

        val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val status = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1

        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL

        val percent = if (level >= 0 && scale > 0) {
            (level * 100) / scale
        } else {
            100
        }
        Pair(percent, isCharging)
    } catch (_: Exception) {
        Pair(100, false)
    }
}
