package com.osauce.launcher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.osauce.launcher.ui.components.EmergencyDock
import com.osauce.launcher.ui.components.IntentionCard
import com.osauce.launcher.ui.components.LiveClock
import com.osauce.launcher.ui.components.SilenceHeader
import com.osauce.launcher.ui.state.rememberSilenceState
import com.osauce.launcher.ui.theme.OledBlack

/**
 * Screen 1 : Le Silence
 *
 * This is the main screen of OpenSauce.
 *
 * Notice how clean and decoupled this file is:
 * - No date/time calculations or battery logic here (handled by rememberSilenceState).
 * - No raw button or drawing code here (handled by modular components in ui/components/).
 * - Its sole responsibility is to arrange the pieces cleanly on an OLED black canvas.
 */
@Composable
fun SilenceScreen(
    onDialer: () -> Unit,
    onCamera: () -> Unit,
    onOpenDefaultHomeSettings: () -> Unit
) {
    // 1. Retrieve display-ready data from our decoupled state provider
    val state = rememberSilenceState()

    // 2. Fullscreen vertical layout on OLED black
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OledBlack)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Header at top, dock at bottom, clock in middle
    ) {
        // --- 1. Top Status Bar ---
        SilenceHeader(batteryStatus = state.batteryStatus)

        // --- 2. Central Section: Clock & Intention ---
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LiveClock(
                timeText = state.time,
                secondsText = state.seconds,
                dateText = state.fullDate
            )

            Spacer(modifier = Modifier.height(28.dp))

            IntentionCard()
        }

        // --- 3. Bottom Action Dock ---
        EmergencyDock(
            onDialer = onDialer,
            onCamera = onCamera,
            onOpenDefaultHomeSettings = onOpenDefaultHomeSettings
        )
    }
}
