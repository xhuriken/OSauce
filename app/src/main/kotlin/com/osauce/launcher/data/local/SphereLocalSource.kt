package com.osauce.launcher.data.local

import android.content.ContentValues
import android.database.Cursor
import com.osauce.launcher.data.model.Sphere
import com.osauce.launcher.data.model.SphereIcon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

/**
 * Data access operations for Spheres table.
 */
class SphereLocalSource(private val dbHelper: OSauceDatabaseHelper) {

    suspend fun getAllSpheres(): List<Sphere> = withContext(Dispatchers.IO) {
        val list = mutableListOf<Sphere>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            OSauceDatabaseHelper.TABLE_SPHERES,
            null,
            null,
            null,
            null,
            null,
            "${OSauceDatabaseHelper.COL_SPHERE_SORT} ASC"
        )

        cursor.use { c ->
            val idIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_SPHERE_ID)
            val nameIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_SPHERE_NAME)
            val iconIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_SPHERE_ICON)
            val colorIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_SPHERE_COLOR)
            val sortIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_SPHERE_SORT)
            val defIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_SPHERE_DEFAULT)
            val pkgIdx = c.getColumnIndexOrThrow(OSauceDatabaseHelper.COL_SPHERE_PACKAGES)

            while (c.moveToNext()) {
                val pkgList = mutableListOf<String>()
                val jsonStr = c.getString(pkgIdx)
                if (!jsonStr.isNullOrBlank()) {
                    val arr = JSONArray(jsonStr)
                    for (i in 0 until arr.length()) {
                        pkgList.add(arr.getString(i))
                    }
                }

                list.add(
                    Sphere(
                        id = c.getString(idIdx),
                        name = c.getString(nameIdx),
                        icon = SphereIcon.fromName(c.getString(iconIdx)),
                        colorHex = c.getString(colorIdx),
                        sortOrder = c.getInt(sortIdx),
                        isDefault = c.getInt(defIdx) == 1,
                        assignedPackages = pkgList
                    )
                )
            }
        }
        list
    }

    suspend fun insertOrUpdate(sphere: Sphere): Unit = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(OSauceDatabaseHelper.COL_SPHERE_ID, sphere.id)
            put(OSauceDatabaseHelper.COL_SPHERE_NAME, sphere.name)
            put(OSauceDatabaseHelper.COL_SPHERE_ICON, sphere.icon.name)
            put(OSauceDatabaseHelper.COL_SPHERE_COLOR, sphere.colorHex)
            put(OSauceDatabaseHelper.COL_SPHERE_SORT, sphere.sortOrder)
            put(OSauceDatabaseHelper.COL_SPHERE_DEFAULT, if (sphere.isDefault) 1 else 0)
            put(OSauceDatabaseHelper.COL_SPHERE_PACKAGES, JSONArray(sphere.assignedPackages).toString())
        }
        db.insertWithOnConflict(
            OSauceDatabaseHelper.TABLE_SPHERES,
            null,
            values,
            android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    suspend fun delete(sphereId: String): Unit = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        db.delete(
            OSauceDatabaseHelper.TABLE_SPHERES,
            "${OSauceDatabaseHelper.COL_SPHERE_ID} = ?",
            arrayOf(sphereId)
        )
    }
}
