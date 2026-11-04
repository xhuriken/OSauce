package com.osauce.launcher.core.designsystem

import android.content.Context
import com.osauce.launcher.data.settings.SettingsPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONArray

/**
 * Theme engine for OpenSauce (Modding Level 1).
 * Handles live theme loading, import/export JSON, and saving custom themes.
 */
class OSauceThemeEngine(context: Context) {

    private val prefs = SettingsPreferences(context.applicationContext)

    private val _customThemes = MutableStateFlow<List<ThemeMod>>(loadSavedCustomThemes())
    val customThemes: StateFlow<List<ThemeMod>> = _customThemes.asStateFlow()

    private val _activeTheme = MutableStateFlow(resolveInitialTheme())
    val activeTheme: StateFlow<ThemeMod> = _activeTheme.asStateFlow()

    private fun resolveInitialTheme(): ThemeMod {
        val activeId = prefs.activeThemeId

        // 1. Check official presets first
        val preset = ThemeMod.Presets.find { it.id == activeId }
        if (preset != null) return preset

        // 2. Check saved custom themes
        val custom = _customThemes.value.find { it.id == activeId }
        if (custom != null) return custom

        // 3. Try to parse active custom JSON
        val customJson = prefs.activeThemeCustomJson
        if (customJson.isNotBlank()) {
            val parsed = ThemeMod.fromJson(customJson)
            if (parsed != null) return parsed
        }

        // 4. Default fallback
        return ThemeMod.Presets.first()
    }

    /**
     * Apply theme live to the whole app.
     */
    fun applyTheme(theme: ThemeMod) {
        prefs.activeThemeId = theme.id
        if (ThemeMod.Presets.none { it.id == theme.id }) {
            prefs.activeThemeCustomJson = theme.toJson()
        } else {
            prefs.activeThemeCustomJson = ""
        }
        _activeTheme.value = theme
    }

    /**
     * Import and validate theme.json file.
     */
    fun importThemeFromJson(jsonStr: String): Result<ThemeMod> {
        val parsed = ThemeMod.fromJson(jsonStr)
            ?: return Result.failure(IllegalArgumentException("Invalid theme JSON format."))

        // Save into custom list
        _customThemes.update { currentList ->
            val filtered = currentList.filterNot { it.id == parsed.id }
            filtered + parsed
        }
        saveCustomThemes(_customThemes.value)

        // Apply right now
        applyTheme(parsed)
        return Result.success(parsed)
    }

    /**
     * Export theme to theme.json format.
     */
    fun exportThemeToJson(theme: ThemeMod): String {
        return theme.toJson()
    }

    /**
     * Delete a custom theme.
     */
    fun deleteCustomTheme(themeId: String) {
        _customThemes.update { current -> current.filterNot { it.id == themeId } }
        saveCustomThemes(_customThemes.value)

        if (_activeTheme.value.id == themeId) {
            applyTheme(ThemeMod.Presets.first())
        }
    }

    /**
     * Get all available themes (presets + custom).
     */
    fun getAllThemes(): List<ThemeMod> {
        return ThemeMod.Presets + _customThemes.value
    }

    private fun loadSavedCustomThemes(): List<ThemeMod> {
        return try {
            val jsonArray = JSONArray(prefs.customThemesListJson)
            val list = mutableListOf<ThemeMod>()
            for (i in 0 until jsonArray.length()) {
                val themeStr = jsonArray.getString(i)
                ThemeMod.fromJson(themeStr)?.let { list.add(it) }
            }
            list
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun saveCustomThemes(themes: List<ThemeMod>) {
        val jsonArray = JSONArray()
        themes.forEach { jsonArray.put(it.toJson()) }
        prefs.customThemesListJson = jsonArray.toString()
    }
}
