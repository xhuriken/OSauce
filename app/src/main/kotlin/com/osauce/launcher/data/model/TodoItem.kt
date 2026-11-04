package com.osauce.launcher.data.model

import java.util.UUID

/**
 * Priority level for a todo item.
 */
enum class TodoPriority {
    LOW,
    NORMAL,
    HIGH,
    CRITICAL;

    companion object {
        fun fromName(name: String): TodoPriority {
            return entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: NORMAL
        }
    }
}

/**
 * Model representing a task or action inside a sphere.
 */
data class TodoItem(
    val id: String = UUID.randomUUID().toString(),
    val sphereId: String,
    val title: String,
    val notes: String? = null,
    val isCompleted: Boolean = false,
    val isPinned: Boolean = false,
    val priority: TodoPriority = TodoPriority.NORMAL,
    val dueTimestamp: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null,
    val sourcePackage: String? = null
)
