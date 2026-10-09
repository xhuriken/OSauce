package com.osauce.launcher.data.model

enum class PluginCategory {
    PRODUCTIVITE,
    AUDIO,
    SANTE_BIENETRE,
    SHADERS_ANIMATIONS,
    WIDGETS_SOBRES,
    COMMUNAUTE
}

data class PluginManifest(
    val id: String,
    val name: String,
    val version: String,
    val author: String,
    val description: String,
    val category: PluginCategory,
    val isInstalled: Boolean = false,
    val isCommunityVerified: Boolean = true,
    val repoUrl: String = "",
    val downloadCount: Int = 0
)
