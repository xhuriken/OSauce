package com.osauce.launcher.core.designsystem

import androidx.compose.ui.graphics.Color

/**
 * OSauce Chromotherapy Color System ("Deep Biophilic Relaxation").
 * 
 * Color Science / Under the hood:
 * - Wavelength: Soft sage green (550nm) and aquatic cyan stimulate the parasympathetic
 *   nervous system (reducing cortisol and lowering heart rate).
 * - Twilight indigo (430-470nm) and Amethyst promote melatonin release and visual quietness.
 * - Deep oceanic base (0x070A11) prevents retinal fatigue without sterile pitch-black gloom.
 */
object OSauceColors {
    // Atmospheric Oceanic Base
    val VoidDark = Color(0xFF070A11)       // Deep oceanic obsidian
    val VoidSurface = Color(0xFF0F1522)    // Velvety slate pad
    val VoidElevated = Color(0xFF172033)   // Elevated controls
    val VoidBorder = Color(0xFF222E47)     // Subdued aquatic border

    // Chromotherapy Calm Accents (Bioluminescent Palette)
    val CalmSage = Color(0xFF34D399)       // Restorative biophilic green
    val CalmMint = Color(0xFF6EE7B7)       // Ethereal particle highlights
    val CalmAqua = Color(0xFF2DD4BF)       // Smooth fluid turquoise
    val CalmCyan = Color(0xFF06B6D4)       // Bioluminescent electric cyan
    val CalmAura = Color(0x2834D399)       // Soft relaxing radial bloom

    // Twilight & Meditative Violet Accents
    val TwilightIndigo = Color(0xFF6366F1) // Circadian twilight depth
    val CalmAmethyst = Color(0xFFA855F7)   // Meditative twilight violet
    val TwilightAura = Color(0x226366F1)

    // Typographic hierarchy
    val TextPrimary = Color(0xFFF1F5F9)
    val TextSecondary = Color(0xFF94A3B8)
    val TextMuted = Color(0xFF475569)

    // Alerts (used strictly when necessary)
    val AlertAmber = Color(0xFFFBBF24)
    val AlertRed = Color(0xFFF43F5E)
}
