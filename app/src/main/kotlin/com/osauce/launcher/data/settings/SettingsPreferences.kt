package com.osauce.launcher.data.settings

import android.content.Context
import android.content.SharedPreferences

/**
 * Settings store backed by SharedPreferences.
 * Uses commit() for immediate sync disk writes.
 */
class SettingsPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("osauce_settings", Context.MODE_PRIVATE)

    var customCalmMessage: String
        get() = prefs.getString(KEY_CALM_MSG, "") ?: ""
        set(value) {
            prefs.edit().putString(KEY_CALM_MSG, value).commit()
        }

    var showSeconds: Boolean
        get() = prefs.getBoolean(KEY_SHOW_SECONDS, false)
        set(value) {
            prefs.edit().putBoolean(KEY_SHOW_SECONDS, value).commit()
        }

    var backgroundVibrancy: String
        get() = prefs.getString(KEY_BG_VIBRANCY, "BALANCED") ?: "BALANCED"
        set(value) {
            prefs.edit().putString(KEY_BG_VIBRANCY, value).commit()
        }

    var cellShapeMode: String
        get() = prefs.getString(KEY_CELL_SHAPE, "ROUNDED_SQUARE") ?: "ROUNDED_SQUARE"
        set(value) {
            prefs.edit().putString(KEY_CELL_SHAPE, value).commit()
        }

    var gridDensity: Int
        get() = prefs.getInt(KEY_GRID_DENSITY, 15)
        set(value) {
            prefs.edit().putInt(KEY_GRID_DENSITY, value).commit()
        }

    var touchParticlesEnabled: Boolean
        get() = prefs.getBoolean(KEY_TOUCH_PARTICLES, true)
        set(value) {
            prefs.edit().putBoolean(KEY_TOUCH_PARTICLES, value).commit()
        }

    var dragParticlesEnabled: Boolean
        get() = prefs.getBoolean(KEY_DRAG_PARTICLES, true)
        set(value) {
            prefs.edit().putBoolean(KEY_DRAG_PARTICLES, value).commit()
        }

    var activeThemeId: String
        get() = prefs.getString(KEY_ACTIVE_THEME_ID, "preset-biophilic") ?: "preset-biophilic"
        set(value) {
            prefs.edit().putString(KEY_ACTIVE_THEME_ID, value).commit()
        }

    var activeThemeCustomJson: String
        get() = prefs.getString(KEY_ACTIVE_THEME_JSON, "") ?: ""
        set(value) {
            prefs.edit().putString(KEY_ACTIVE_THEME_JSON, value).commit()
        }

    var customThemesListJson: String
        get() = prefs.getString(KEY_CUSTOM_THEMES_JSON, "[]") ?: "[]"
        set(value) {
            prefs.edit().putString(KEY_CUSTOM_THEMES_JSON, value).commit()
        }

    companion object {
        private const val KEY_CALM_MSG = "key_calm_message"
        private const val KEY_SHOW_SECONDS = "key_show_seconds"
        private const val KEY_BG_VIBRANCY = "key_bg_vibrancy"
        private const val KEY_CELL_SHAPE = "key_cell_shape"
        private const val KEY_GRID_DENSITY = "key_grid_density"
        private const val KEY_TOUCH_PARTICLES = "key_touch_particles"
        private const val KEY_DRAG_PARTICLES = "key_drag_particles"
        private const val KEY_ACTIVE_THEME_ID = "key_active_theme_id"
        private const val KEY_ACTIVE_THEME_JSON = "key_active_theme_json"
        private const val KEY_CUSTOM_THEMES_JSON = "key_custom_themes_json"
    }
}
