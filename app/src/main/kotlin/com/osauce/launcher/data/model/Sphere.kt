package com.osauce.launcher.data.model

import java.util.UUID

/**
 * Supported vector icon keys for life spheres.
 */
enum class SphereIcon {
    USERS,
    BRIEFCASE,
    PULSE,
    LEAF,
    STAR,
    SHIELD,
    TERMINAL,
    COMPASS;

    companion object {
        fun fromName(name: String): SphereIcon {
            return entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: STAR
        }
    }
}

/**
 * Model representing a Life Sphere (Group context).
 */
data class Sphere(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val icon: SphereIcon = SphereIcon.STAR,
    val colorHex: String = "#60A5FA",
    val sortOrder: Int = 0,
    val isDefault: Boolean = false,
    val assignedPackages: List<String> = emptyList()
)
