package com.osauce.launcher.data.local

import android.content.ContentValues
import android.database.Cursor
import com.osauce.launcher.data.model.TodoItem
import com.osauce.launcher.data.model.TodoPriority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Data access operations for Todos table.
 */
class TodoLocalSource(private val dbHelper: OSauceDatabaseHelper) {

    suspend fun getAllTodos(): List<TodoItem> = withContext(Dispatchers.IO) {
        val list = mutableListOf<TodoItem>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            OSauceDatabaseHelper.TABLE_TODOS,
            null,
            null,
            null,
            null,
            null,
            "${OSauceDatabaseHelper.COL_TODO_COMPLETED} ASC, ${OSauceDatabaseHelper.COL_TODO_PINNED} DESC, ${OSauceDatabaseHelper.COL_TODO_CREATED} DESC"
        )

        cursor.use { c ->
            val idIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_ID)
            val sphereIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_SPHERE_ID)
            val titleIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_TITLE)
            val notesIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_NOTES)
            val compIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_COMPLETED)
            val pinIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_PINNED)
            val prioIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_PRIORITY)
            val dueIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_DUE)
            val createdIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_CREATED)
            val compAtIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_COMPLETED_AT)
            val srcIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_TODO_SOURCE_PKG)

            while (c.moveToNext()) {
                val dueVal = if (c.isNull(dueIdx)) null else c.getLong(dueIdx)
                val compAtVal = if (c.isNull(compAtIdx)) null else c.getLong(compAtIdx)

                list.add(
                    TodoItem(
                        id = c.getString(idIdx),
                        sphereId = c.getString(sphereIdx),
                        title = c.getString(titleIdx),
                        notes = c.getString(notesIdx),
                        isCompleted = c.getInt(compIdx) == 1,
                        isPinned = c.getInt(pinIdx) == 1,
                        priority = TodoPriority.fromName(c.getString(prioIdx)),
                        dueTimestamp = dueVal,
                        createdAt = c.getLong(createdIdx),
                        completedAt = compAtVal,
                        sourcePackage = c.getString(srcIdx)
                    )
                )
            }
        }
        list
    }

    suspend fun insertOrUpdate(todo: TodoItem): Unit = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(OSauceDatabaseHelper.COL_TODO_ID, todo.id)
            put(OSauceDatabaseHelper.COL_TODO_SPHERE_ID, todo.sphereId)
            put(OSauceDatabaseHelper.COL_TODO_TITLE, todo.title)
            put(OSauceDatabaseHelper.COL_TODO_NOTES, todo.notes)
            put(OSauceDatabaseHelper.COL_TODO_COMPLETED, if (todo.isCompleted) 1 else 0)
            put(OSauceDatabaseHelper.COL_TODO_PINNED, if (todo.isPinned) 1 else 0)
            put(OSauceDatabaseHelper.COL_TODO_PRIORITY, todo.priority.name)
            if (todo.dueTimestamp != null) {
                put(OSauceDatabaseHelper.COL_TODO_DUE, todo.dueTimestamp)
            } else {
                putNull(OSauceDatabaseHelper.COL_TODO_DUE)
            }
            put(OSauceDatabaseHelper.COL_TODO_CREATED, todo.createdAt)
            if (todo.completedAt != null) {
                put(OSauceDatabaseHelper.COL_TODO_COMPLETED_AT, todo.completedAt)
            } else {
                putNull(OSauceDatabaseHelper.COL_TODO_COMPLETED_AT)
            }
            put(OSauceDatabaseHelper.COL_TODO_SOURCE_PKG, todo.sourcePackage)
        }
        db.insertWithOnConflict(
            OSauceDatabaseHelper.TABLE_TODOS,
            null,
            values,
            android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    suspend fun toggleCompleted(todoId: String, completed: Boolean): Unit = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(OSauceDatabaseHelper.COL_TODO_COMPLETED, if (completed) 1 else 0)
            if (completed) {
                put(OSauceDatabaseHelper.COL_TODO_COMPLETED_AT, System.currentTimeMillis())
            } else {
                putNull(OSauceDatabaseHelper.COL_TODO_COMPLETED_AT)
            }
        }
        db.update(
            OSauceDatabaseHelper.TABLE_TODOS,
            values,
            "${OSauceDatabaseHelper.COL_TODO_ID} = ?",
            arrayOf(todoId)
        )
    }

    suspend fun delete(todoId: String): Unit = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        db.delete(
            OSauceDatabaseHelper.TABLE_TODOS,
            "${OSauceDatabaseHelper.COL_TODO_ID} = ?",
            arrayOf(todoId)
        )
    }
}
