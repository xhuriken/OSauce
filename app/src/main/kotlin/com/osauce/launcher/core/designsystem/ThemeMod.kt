package com.osauce.launcher.core.designsystem

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import org.json.JSONObject

@Immutable
data class ThemeModColors(
    val background: Color,
    val surface: Color,
    val elevated: Color,
    val border: Color,
    val primaryAccent: Color,
    val secondaryAccent: Color,
    val textPrimary: Color,
    val textSecondary: Color
) {
    companion object {
        val Default = ThemeModColors(
            background = OSauceColors.VoidDark,
            surface = OSauceColors.VoidSurface,
            elevated = OSauceColors.VoidElevated,
            border = OSauceColors.VoidBorder,
            primaryAccent = OSauceColors.CalmSage,
            secondaryAccent = OSauceColors.CalmAqua,
            textPrimary = OSauceColors.TextPrimary,
            textSecondary = OSauceColors.TextSecondary
        )

        val PureOled = ThemeModColors(
            background = Color(0xFF000000),
            surface = Color(0xFF0D0D0D),
            elevated = Color(0xFF1A1A1A),
            border = Color(0xFF262626),
            primaryAccent = Color(0xFFE5E5E5),
            secondaryAccent = Color(0xFFA3A3A3),
            textPrimary = Color(0xFFFAFAFA),
            textSecondary = Color(0xFF737373)
        )

        val AmberMinimal = ThemeModColors(
            background = Color(0xFF000000),
            surface = Color(0xFF100C08),
            elevated = Color(0xFF1E1710),
            border = Color(0xFF332517),
            primaryAccent = Color(0xFFFFB300),
            secondaryAccent = Color(0xFFFF8F00),
            textPrimary = Color(0xFFFFE082),
            textSecondary = Color(0xFFFFA000)
        )
    }
}

@Immutable
data class ThemeMod(
    val id: String,
    val name: String,
    val author: String,
    val version: String,
    val description: String,
    val colors: ThemeModColors,
    val cellShape: CellShapeMode = CellShapeMode.ROUNDED_SQUARE,
    val vibrancy: BackgroundVibrancy = BackgroundVibrancy.BALANCED
) {
    fun toJson(): String {
        val json = JSONObject()
        json.put("id", id)
        json.put("name", name)
        json.put("author", author)
        json.put("version", version)
        json.put("description", description)
        json.put("cellShape", cellShape.name)
        json.put("vibrancy", vibrancy.name)

        val colorsJson = JSONObject()
        colorsJson.put("background", colors.background.toHex())
        colorsJson.put("surface", colors.surface.toHex())
        colorsJson.put("elevated", colors.elevated.toHex())
        colorsJson.put("border", colors.border.toHex())
        colorsJson.put("primaryAccent", colors.primaryAccent.toHex())
        colorsJson.put("secondaryAccent", colors.secondaryAccent.toHex())
        colorsJson.put("textPrimary", colors.textPrimary.toHex())
        colorsJson.put("textSecondary", colors.textSecondary.toHex())
        json.put("colors", colorsJson)

        return json.toString(2)
    }

    companion object {
        fun fromJson(jsonStr: String): ThemeMod? {
            return try {
                val json = JSONObject(jsonStr)
                val colorsObj = json.optJSONObject("colors")

                val colors = if (colorsObj != null) {
                    ThemeModColors(
                        background = parseColor(colorsObj.optString("background", "#070A11")),
                        surface = parseColor(colorsObj.optString("surface", "#0F1522")),
                        elevated = parseColor(colorsObj.optString("elevated", "#172033")),
                        border = parseColor(colorsObj.optString("border", "#222E47")),
                        primaryAccent = parseColor(colorsObj.optString("primaryAccent", "#34D399")),
                        secondaryAccent = parseColor(colorsObj.optString("secondaryAccent", "#2DD4BF")),
                        textPrimary = parseColor(colorsObj.optString("textPrimary", "#F1F5F9")),
                        textSecondary = parseColor(colorsObj.optString("textSecondary", "#94A3B8"))
                    )
                } else {
                    ThemeModColors.Default
                }

                ThemeMod(
                    id = json.optString("id", "custom-theme"),
                    name = json.optString("name", "Custom Theme"),
                    author = json.optString("author", "Community"),
                    version = json.optString("version", "1.0.0"),
                    description = json.optString("description", ""),
                    colors = colors,
                    cellShape = CellShapeMode.fromName(json.optString("cellShape", "ROUNDED_SQUARE")),
                    vibrancy = BackgroundVibrancy.fromName(json.optString("vibrancy", "BALANCED"))
                )
            } catch (_: Exception) {
                null
            }
        }

        private fun parseColor(hex: String): Color {
            val cleanHex = hex.removePrefix("#")
            val colorLong = when (cleanHex.length) {
                6 -> ("FF$cleanHex").toLong(16)
                8 -> cleanHex.toLong(16)
                else -> 0xFFFFFFFF
            }
            return Color(colorLong)
        }

        private fun Color.toHex(): String {
            val a = (alpha * 255).toInt()
            val r = (red * 255).toInt()
            val g = (green * 255).toInt()
            val b = (blue * 255).toInt()
            return String.format("#%02X%02X%02X%02X", a, r, g, b)
        }

        val Presets = listOf(
            ThemeMod(
                id = "preset-biophilic",
                name = "Deep Biophilic",
                author = "OSauce Core",
                version = "1.0.0",
                description = "Calming sage green and oceanic cyan.",
                colors = ThemeModColors.Default,
                cellShape = CellShapeMode.ROUNDED_SQUARE,
                vibrancy = BackgroundVibrancy.BALANCED
            ),
            ThemeMod(
                id = "preset-oled-pure",
                name = "Pure OLED Minimal",
                author = "OSauce Core",
                version = "1.0.0",
                description = "True OLED pitch black with clean greys.",
                colors = ThemeModColors.PureOled,
                cellShape = CellShapeMode.CROSS_MATRIX,
                vibrancy = BackgroundVibrancy.CALM
            ),
            ThemeMod(
                id = "preset-amber",
                name = "Amber Minimal",
                author = "OSauce Core",
                version = "1.0.0",
                description = "Warm 590nm amber, easy on eyes at night.",
                colors = ThemeModColors.AmberMinimal,
                cellShape = CellShapeMode.ORGANIC_CELL,
                vibrancy = BackgroundVibrancy.CALM
            )
        )
    }
}
