package com.osauce.launcher.data.model

data class ActionNotification(
    val id: String,
    val sender: String,
    val message: String,
    val timestamp: String,
    val packageName: String,
    val actionLabel: String? = null
)

data class ActionTodo(
    val id: String,
    val text: String,
    var isDone: Boolean = false,
    val linkedPackageName: String? = null,
    val actionLabel: String? = null
)

data class ActionGroup(
    val id: String,
    val title: String,
    val statusText: String,
    val notifications: List<ActionNotification> = emptyList(),
    val todos: List<ActionTodo> = emptyList(),
    val appShortcuts: List<String> = emptyList(),
    val isMuted: Boolean = false
)
