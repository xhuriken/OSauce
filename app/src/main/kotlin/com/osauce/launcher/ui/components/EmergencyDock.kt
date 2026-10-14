package com.osauce.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.ui.theme.*

/**
 * Bottom action dock on the Silence screen.
 * Contains:
 * 1. 3 essential physical-feel buttons: Call, Silence (Focus), Camera.
 * 2. A discreet button to open Android system settings to manage or disable the default launcher.
 */
@Composable
fun EmergencyDock(
    onDialer: () -> Unit,
    onCamera: () -> Unit,
    onOpenDefaultHomeSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Row of 3 tactile action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Phone Dialer Button
            CustomPressable(
                onClick = onDialer,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(CardDark)
                    .border(1.dp, BorderDark, CircleShape)
            ) {
                Text(
                    text = "APPEL",
                    color = TextPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
            }

            // 2. Central Presence / Silence Button
            CustomPressable(
                onClick = { /* Mindfulness action or breathing pulse */ },
                modifier = Modifier
                    .weight(1.4f)
                    .height(48.dp)
                    .background(CardDarkVariant)
                    .border(1.dp, SageGreen.copy(alpha = 0.4f), CircleShape)
            ) {
                Text(
                    text = "SILENCE",
                    color = SageGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }

            // 3. Camera Button
            CustomPressable(
                onClick = onCamera,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(CardDark)
                    .border(1.dp, BorderDark, CircleShape)
            ) {
                Text(
                    text = "PHOTO",
                    color = TextPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Discrete option to open Android's Default Home app picker
        CustomPressable(
            onClick = onOpenDefaultHomeSettings,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = "⚙ Gérer / Désactiver le launcher par défaut",
                color = TextMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.3.sp
            )
        }
    }
}
