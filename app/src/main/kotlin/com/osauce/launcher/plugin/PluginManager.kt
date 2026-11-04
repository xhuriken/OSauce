package com.osauce.launcher.plugin

import com.osauce.launcher.data.model.PluginCategory
import com.osauce.launcher.data.model.PluginManifest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Mod and plugin manager for OSauce.
 * Handles local catalog, default plugins, and live enable/disable.
 */
class PluginManager {

    private val defaultBundled = listOf(
        PluginManifest(
            id = "org.osauce.theme.biophilic",
            name = "Deep Biophilic Theme",
            version = "1.0.0",
            author = "OSauce Core",
            description = "Calming sage and oceanic cyan chromotherapy palette.",
            category = PluginCategory.THEMES,
            isInstalled = true,
            isCommunityVerified = true,
            repoUrl = "https://github.com/xhuriken/OSauce",
            downloadCount = 1250
        ),
        PluginManifest(
            id = "org.osauce.theme.oled",
            name = "Pure OLED Minimal",
            version = "1.0.0",
            author = "OSauce Core",
            description = "True OLED pitch black for max battery life.",
            category = PluginCategory.THEMES,
            isInstalled = true,
            isCommunityVerified = true,
            repoUrl = "https://github.com/xhuriken/OSauce",
            downloadCount = 2100
        ),
        PluginManifest(
            id = "org.osauce.shader.organic-waves",
            name = "Organic Waves AGSL",
            version = "1.1.0",
            author = "OSauce Lab",
            description = "Fluid wave background computed with AGSL shader at 120 FPS.",
            category = PluginCategory.SHADERS_ANIMATIONS,
            isInstalled = true,
            isCommunityVerified = true,
            repoUrl = "https://github.com/xhuriken/OSauce",
            downloadCount = 890
        ),
        PluginManifest(
            id = "org.osauce.widget.pomodoro",
            name = "Calm Pomodoro Focus",
            version = "1.0.2",
            author = "alex_dev",
            description = "Minimal focus timer with soft alerts on Silence Screen.",
            category = PluginCategory.PRODUCTIVITE,
            isInstalled = false,
            isCommunityVerified = true,
            repoUrl = "https://github.com/alex_dev/osauce-pomodoro",
            downloadCount = 340
        )
    )

    private val _installedPlugins = MutableStateFlow<List<PluginManifest>>(defaultBundled)
    val installedPlugins: StateFlow<List<PluginManifest>> = _installedPlugins.asStateFlow()

    fun toggleInstall(pluginId: String) {
        _installedPlugins.update { list ->
            list.map { plugin ->
                if (plugin.id == pluginId) {
                    plugin.copy(isInstalled = !plugin.isInstalled)
                } else {
                    plugin
                }
            }
        }
    }

    fun search(query: String, category: PluginCategory? = null): List<PluginManifest> {
        return _installedPlugins.value.filter { plugin ->
            val matchesQuery = query.isBlank() ||
                    plugin.name.contains(query, ignoreCase = true) ||
                    plugin.description.contains(query, ignoreCase = true)
            val matchesCategory = category == null || plugin.category == category
            matchesQuery && matchesCategory
        }
    }
}
