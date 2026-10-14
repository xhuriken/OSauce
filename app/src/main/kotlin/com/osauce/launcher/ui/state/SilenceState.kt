package com.osauce.launcher.ui.state

import android.content.Context
import android.os.BatteryManager
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Data class representing all the visual data needed for the Silence screen.
 * This separates raw data logic (clock, battery, formatting) from the UI components.
 */
data class SilenceDisplayData(
    val time: String,          // Example: "05:24"
    val seconds: String,       // Example: "48"
    val fullDate: String,      // Example: "Mercredi 14 octobre 2026"
    val batteryStatus: String  // Example: "85% · Veille sobre"
)

/**
 * Custom State Hook (like a React hook).
 * Handles the background loop that updates the time every second
 * and converts raw Android system values into clean, display-ready strings.
 */
@Composable
fun rememberSilenceState(): SilenceDisplayData {
    // LocalContext gives us access to Android system services (like battery)
    val context = LocalContext.current

    // 'mutableStateOf' tells Compose: "if this value changes, redraw the parts of UI using it"
    var currentTime by remember { mutableStateOf(LocalTime.now()) }
    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    // 'LaunchedEffect' starts a coroutine (background task) tied to this screen lifecycle.
    // 'Unit' as key means: "start once when screen opens, keep running until screen closes".
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            currentDate = LocalDate.now()
            delay(1000L) // Wait exactly 1 second before next tick
        }
    }

    // Formatters: reusable patterns to convert LocalTime/LocalDate to readable strings
    val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm") }
    val secondsFormatter = remember { DateTimeFormatter.ofPattern("ss") }
    val dateFormatter = remember { DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.FRENCH) }

    val formattedTime = currentTime.format(timeFormatter)
    val formattedSeconds = currentTime.format(secondsFormatter)
    val formattedDate = currentDate.format(dateFormatter)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.FRENCH) else it.toString() }

    // Read real battery capacity from Android BatteryManager
    // We re-query it only when minute changes to save CPU/battery
    val batteryPercentage = remember(currentTime.minute) {
        try {
            val bm = context.getSystemService(Context.BATTERY_SERVICE) as? BatteryManager
            val capacity = bm?.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) ?: -1
            if (capacity in 0..100) "$capacity%" else "100%"
        } catch (_: Exception) {
            "100%"
        }
    }

    return SilenceDisplayData(
        time = formattedTime,
        seconds = formattedSeconds,
        fullDate = formattedDate,
        batteryStatus = "$batteryPercentage"
    )
}
