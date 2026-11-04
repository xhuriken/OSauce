package com.osauce.launcher.data.repository

import com.osauce.launcher.data.local.TodoLocalSource
import com.osauce.launcher.data.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository providing reactive and cached access to todos.
 */
class TodoRepository(private val localSource: TodoLocalSource) {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()

    suspend fun loadTodos() {
        val list = localSource.getAllTodos()
        _todos.value = list
    }

    suspend fun addTodo(todo: TodoItem) {
        localSource.insertOrUpdate(todo)
        loadTodos()
    }

    suspend fun toggleCompleted(todoId: String, isCompleted: Boolean) {
        localSource.toggleCompleted(todoId, isCompleted)
        loadTodos()
    }

    suspend fun deleteTodo(todoId: String) {
        localSource.delete(todoId)
        loadTodos()
    }
}
