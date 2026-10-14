package com.osauce.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.ui.theme.*

/**
 * Central hero clock component.
 * Displays:
 * 1. Large minimalist hours:minutes.
 * 2. Small monospace badge showing live seconds.
 * 3. Full localized date string below.
 */
@Composable
fun LiveClock(
    timeText: String,
    secondsText: String,
    dateText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Main time row: HH:mm + seconds counter
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = timeText,
                color = TextPrimary,
                fontSize = 76.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = (-3).sp,
                lineHeight = 76.sp
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Seconds badge
            Box(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(CardDarkVariant)
                    .border(1.dp, BorderDark, RoundedCornerShape(6.dp))
                    .padding(horizontal = 7.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "$secondsText s",
                    color = SageGreen,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }
        }

        // Full localized date
        Text(
            text = dateText,
            color = TextSecondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
