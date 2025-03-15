package com.project.petfinder.core.data.repository

import com.project.petfinder.core.data.remote.LocationApiService
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiService: LocationApiService
) : LocationRepository {

    override suspend fun getMunicipalities(): List<Municipality> = withContext(Dispatchers.IO) {
        try {
            apiService.getMunicipalities().map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}