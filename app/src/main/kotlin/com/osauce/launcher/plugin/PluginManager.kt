package com.osauce.launcher.plugin

import com.osauce.launcher.data.model.PluginCategory
import com.osauce.launcher.data.model.PluginManifest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PluginManager {

    private val _installedPlugins = MutableStateFlow<List<PluginManifest>>(emptyList())
    val installedPlugins: StateFlow<List<PluginManifest>> = _installedPlugins.asStateFlow()

    private val _marketplacePlugins = MutableStateFlow<List<PluginManifest>>(
        listOf(
            PluginManifest(
                id = "org.osauce.pomodoro",
                name = "Chrono Focus Pomodoro",
                version = "1.0.2",
                author = "alex_dev",
                description = "Minuteur de travail sobre sans alarme stridente, integre a l'Ecran 1.",
                category = PluginCategory.PRODUCTIVITE,
                isInstalled = true,
                downloadCount = 1420
            ),
            PluginManifest(
                id = "com.community.solar-arc",
                name = "Cadran Solaire 24H",
                version = "2.1.0",
                author = "luna_astronomy",
                description = "Shader AGSL de la course du soleil et des etoiles autour de l'horloge.",
                category = PluginCategory.SHADERS_ANIMATIONS,
                isInstalled = false,
                downloadCount = 3850
            ),
            PluginManifest(
                id = "io.osauce.obsidian-bridge",
                name = "Passerelle Notes Markdown",
                version = "1.1.0",
                author = "marx_foss",
                description = "Synchronise les Todos des Groupes directement dans votre coffre Obsidian local.",
                category = PluginCategory.PRODUCTIVITE,
                isInstalled = false,
                downloadCount = 2100
            ),
            PluginManifest(
                id = "org.community.audio-zen",
                name = "Mini Lecteur Audio Blanc",
                version = "1.0.0",
                author = "sound_smith",
                description = "Controle minimaliste des flux audio VLC et Spotify sans ouvrir l'application.",
                category = PluginCategory.AUDIO,
                isInstalled = false,
                downloadCount = 980
            )
        )
    )
    val marketplacePlugins: StateFlow<List<PluginManifest>> = _marketplacePlugins.asStateFlow()

    fun toggleInstallPlugin(pluginId: String) {
        val currentList = _marketplacePlugins.value
        _marketplacePlugins.value = currentList.map { plugin ->
            if (plugin.id == pluginId) {
                plugin.copy(isInstalled = !plugin.isInstalled)
            } else {
                plugin
            }
        }
    }
}
