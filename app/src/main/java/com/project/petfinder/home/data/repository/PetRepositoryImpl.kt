package com.project.petfinder.home.data.repository

import com.project.petfinder.home.data.remote.PetApiService
import com.project.petfinder.home.data.dto.PetResponse
import com.project.petfinder.home.data.dto.ReportPetRequest
import com.project.petfinder.home.domain.model.Pet
import com.project.petfinder.home.domain.repository.PetRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PetRepositoryImpl @Inject constructor(
    private val apiService: PetApiService
) : PetRepository {

    override suspend fun getLostPets(): List<Pet> = withContext(Dispatchers.IO) {
        try {
            apiService.getLostPets().map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun reportRescue(petId: String) = withContext(Dispatchers.IO) {
        try {
            apiService.reportRescue(ReportPetRequest(petId))
        } catch (e: Exception) {
            // Manejar error según necesidades
        }
    }

    override suspend fun reportSighting(petId: String) = withContext(Dispatchers.IO) {
        try {
            apiService.reportSighting(ReportPetRequest(petId))
        } catch (e: Exception) {
            // Manejar error según necesidades
        }
    }

    private fun PetResponse.toDomain(): Pet = Pet(
        id = this.id,
        name = this.name,
        description = this.description ?: "",
        imageUrl = this.imageUrl,
        ownerName = this.ownerName ?: "",
        lastSeenLocation = this.lastSeenLocation,
        lastSeenDate = this.lastSeenDate ?: ""
    )
}