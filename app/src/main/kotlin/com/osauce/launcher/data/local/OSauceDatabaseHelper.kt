package com.osauce.launcher.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.osauce.launcher.data.model.Sphere
import com.osauce.launcher.data.model.SphereIcon
import com.osauce.launcher.data.model.TodoItem
import com.osauce.launcher.data.model.TodoPriority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

/**
 * Local SQLite database for offline spheres and todos.
 */
class OSauceDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "osauce_local.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_SPHERES = "spheres"
        const val COL_SPHERE_ID = "id"
        const val COL_SPHERE_NAME = "name"
        const val COL_SPHERE_ICON = "icon"
        const val COL_SPHERE_COLOR = "color_hex"
        const val COL_SPHERE_SORT = "sort_order"
        const val COL_SPHERE_DEFAULT = "is_default"
        const val COL_SPHERE_PACKAGES = "assigned_packages"

        const val TABLE_TODOS = "todos"
        const val COL_TODO_ID = "id"
        const val COL_TODO_SPHERE_ID = "sphere_id"
        const val COL_TODO_TITLE = "title"
        const val COL_TODO_NOTES = "notes"
        const val COL_TODO_COMPLETED = "is_completed"
        const val COL_TODO_PINNED = "is_pinned"
        const val COL_TODO_PRIORITY = "priority"
        const val COL_TODO_DUE = "due_timestamp"
        const val COL_TODO_CREATED = "created_at"
        const val COL_TODO_COMPLETED_AT = "completed_at"
        const val COL_TODO_SOURCE_PKG = "source_package"

        @Volatile
        private var instance: OSauceDatabaseHelper? = null

        fun getInstance(context: Context): OSauceDatabaseHelper {
            return instance ?: synchronized(this) {
                instance ?: OSauceDatabaseHelper(context.applicationContext).also { instance = it }
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createSpheres = """
            CREATE TABLE $TABLE_SPHERES (
                $COL_SPHERE_ID TEXT PRIMARY KEY,
                $COL_SPHERE_NAME TEXT NOT NULL,
                $COL_SPHERE_ICON TEXT NOT NULL,
                $COL_SPHERE_COLOR TEXT NOT NULL,
                $COL_SPHERE_SORT INTEGER NOT NULL,
                $COL_SPHERE_DEFAULT INTEGER NOT NULL,
                $COL_SPHERE_PACKAGES TEXT NOT NULL
            )
        """.trimIndent()

        val createTodos = """
            CREATE TABLE $TABLE_TODOS (
                $COL_TODO_ID TEXT PRIMARY KEY,
                $COL_TODO_SPHERE_ID TEXT NOT NULL,
                $COL_TODO_TITLE TEXT NOT NULL,
                $COL_TODO_NOTES TEXT,
                $COL_TODO_COMPLETED INTEGER NOT NULL DEFAULT 0,
                $COL_TODO_PINNED INTEGER NOT NULL DEFAULT 0,
                $COL_TODO_PRIORITY TEXT NOT NULL DEFAULT 'NORMAL',
                $COL_TODO_DUE INTEGER,
                $COL_TODO_CREATED INTEGER NOT NULL,
                $COL_TODO_COMPLETED_AT INTEGER,
                $COL_TODO_SOURCE_PKG TEXT,
                FOREIGN KEY ($COL_TODO_SPHERE_ID) REFERENCES $TABLE_SPHERES($COL_SPHERE_ID) ON DELETE CASCADE
            )
        """.trimIndent()

        db.execSQL(createSpheres)
        db.execSQL(createTodos)

        // Insert default initial spheres
        insertDefaultSpheres(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Migration logic for future schema changes
    }

    private fun insertDefaultSpheres(db: SQLiteDatabase) {
        val defaults = listOf(
            Sphere(
                id = "sphere_humans",
                name = "Humains",
                icon = SphereIcon.USERS,
                colorHex = "#4ADE80",
                sortOrder = 0,
                isDefault = true,
                assignedPackages = listOf("com.google.android.dialer", "com.google.android.apps.messaging", "com.whatsapp", "org.thoughtcrime.securesms")
            ),
            Sphere(
                id = "sphere_work",
                name = "Travail",
                icon = SphereIcon.BRIEFCASE,
                colorHex = "#60A5FA",
                sortOrder = 1,
                isDefault = true,
                assignedPackages = listOf("com.google.android.gm", "com.slack", "notion.id")
            ),
            Sphere(
                id = "sphere_vital",
                name = "Vital",
                icon = SphereIcon.PULSE,
                colorHex = "#FBBF24",
                sortOrder = 2,
                isDefault = true,
                assignedPackages = listOf("com.sncf.fusion.mobile", "fr.sncf.transilien")
            ),
            Sphere(
                id = "sphere_leisure",
                name = "Loisirs",
                icon = SphereIcon.LEAF,
                colorHex = "#A78BFA",
                sortOrder = 3,
                isDefault = true,
                assignedPackages = listOf("com.spotify.music", "com.google.android.apps.youtube.music")
            )
        )

        for (sphere in defaults) {
            val values = ContentValues().apply {
                put(COL_SPHERE_ID, sphere.id)
                put(COL_SPHERE_NAME, sphere.name)
                put(COL_SPHERE_ICON, sphere.icon.name)
                put(COL_SPHERE_COLOR, sphere.colorHex)
                put(COL_SPHERE_SORT, sphere.sortOrder)
                put(COL_SPHERE_DEFAULT, if (sphere.isDefault) 1 else 0)
                put(COL_SPHERE_PACKAGES, JSONArray(sphere.assignedPackages).toString())
            }
            db.insert(TABLE_SPHERES, null, values)
        }
    }
}
