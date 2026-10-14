package com.osauce.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.ui.theme.BorderDark
import com.osauce.launcher.ui.theme.CardDark
import com.osauce.launcher.ui.theme.TextPrimary

/**
 * Calm intention card placed under the clock.
 * Designed to replace compulsive notification badges with a reminder to breathe.
 */
@Composable
fun IntentionCard(
    text: String = "je suis quelquechose, qui sert pas...",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(CardDark)
            .border(1.dp, BorderDark, CircleShape)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.5.sp
        )
    }
}
