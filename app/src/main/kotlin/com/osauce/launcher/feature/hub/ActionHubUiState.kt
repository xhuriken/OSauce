package com.osauce.launcher.feature.hub

import com.osauce.launcher.data.model.Sphere
import com.osauce.launcher.data.model.TodoItem

/**
 * Immutable UI state for Action Hub screen.
 */
data class ActionHubUiState(
    val spheres: List<Sphere> = emptyList(),
    val selectedSphereId: String? = null,
    val todos: List<TodoItem> = emptyList(),
    val quickInputText: String = "",
    val isLoading: Boolean = false
) {
    /**
     * Filtered todos according to active sphere selection.
     * When selectedSphereId is null, returns all todos.
     */
    val filteredTodos: List<TodoItem>
        get() = if (selectedSphereId == null) {
            todos
        } else {
            todos.filter { it.sphereId == selectedSphereId }
        }

    val activeTodos: List<TodoItem>
        get() = filteredTodos.filter { !it.isCompleted }

    val completedTodos: List<TodoItem>
        get() = filteredTodos.filter { it.isCompleted }
}
