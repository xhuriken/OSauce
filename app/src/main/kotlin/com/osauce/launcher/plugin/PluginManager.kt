package com.osauce.launcher.plugin

import com.osauce.launcher.data.model.PluginManifest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Gestionnaire de mods et plugins communautaires (À venir).
 */
class PluginManager {
    private val _installedPlugins = MutableStateFlow<List<PluginManifest>>(emptyList())
    val installedPlugins: StateFlow<List<PluginManifest>> = _installedPlugins.asStateFlow()
}
