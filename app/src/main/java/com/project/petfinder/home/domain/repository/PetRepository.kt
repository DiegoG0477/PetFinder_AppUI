package com.project.petfinder.home.domain.repository

import com.project.petfinder.home.domain.model.Pet
import com.project.petfinder.core.domain.model.OperationResult

interface PetRepository {
    suspend fun getLostPets(): Result<List<Pet>>
    suspend fun reportRescue(petId: String): Result<OperationResult>
    suspend fun reportSighting(petId: String): Result<OperationResult>
}