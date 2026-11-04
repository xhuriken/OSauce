package com.osauce.launcher.core.designsystem

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.osauce.launcher.R

val SpaceGrotesk = FontFamily(
    Font(resId = R.font.space_grotesk, weight = FontWeight.Bold),
    Font(resId = R.font.space_grotesk, weight = FontWeight.SemiBold),
    Font(resId = R.font.space_grotesk, weight = FontWeight.Medium)
)

object OSauceTypography {
    // Discrete, balanced monument clock
    val FluidClock = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 56.sp,
        letterSpacing = (-1).sp,
        color = OSauceColors.TextPrimary
    )

    // Animated seconds display (accent color, slightly lighter weight)
    val SecondsClock = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        letterSpacing = 0.sp,
        color = OSauceColors.CalmSage
    )

    // Crisp secondary ticker (Date, Battery, Status)
    val FluidTicker = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        letterSpacing = 0.8.sp,
        color = OSauceColors.TextSecondary
    )

    // Section title
    val SectionHeader = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 1.5.sp,
        color = OSauceColors.TextMuted
    )

    // Action item titles
    val TileTitle = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        letterSpacing = 0.sp,
        color = OSauceColors.TextPrimary
    )

    // Technical action tag
    val TileTag = TextStyle(
        fontFamily = SpaceGrotesk,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        letterSpacing = 0.8.sp,
        color = OSauceColors.CalmSage
    )
}
