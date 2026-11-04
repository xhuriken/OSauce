package com.osauce.launcher.data.repository

import com.osauce.launcher.data.local.SphereLocalSource
import com.osauce.launcher.data.model.Sphere
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository providing reactive and cached access to spheres.
 */
class SphereRepository(private val localSource: SphereLocalSource) {

    private val _spheres = MutableStateFlow<List<Sphere>>(emptyList())
    val spheres: StateFlow<List<Sphere>> = _spheres.asStateFlow()

    suspend fun loadSpheres() {
        val list = localSource.getAllSpheres()
        _spheres.value = list
    }

    suspend fun saveSphere(sphere: Sphere) {
        localSource.insertOrUpdate(sphere)
        loadSpheres()
    }

    suspend fun deleteSphere(sphereId: String) {
        localSource.delete(sphereId)
        loadSpheres()
    }
}
