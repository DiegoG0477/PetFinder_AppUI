package com.project.petfinder.home.data.repository

import com.project.petfinder.home.data.remote.PetApiService
import com.project.petfinder.home.data.dto.ReportPetDto
import com.project.petfinder.home.data.mapper.toDomain
import com.project.petfinder.home.domain.model.Pet
import com.project.petfinder.home.domain.repository.PetRepository
import com.project.petfinder.core.domain.model.OperationResult
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PetRepositoryImpl @Inject constructor(
    private val apiService: PetApiService
) : PetRepository {

    override suspend fun getLostPets(): Result<List<Pet>> = withContext(Dispatchers.IO) {
        try {
            Result.success(apiService.getLostPets().map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun reportRescue(petId: String): Result<OperationResult> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.reportRescue(ReportPetDto(petId))
            if (response.success) {
                Result.success(OperationResult.Success(response.message))
            } else {
                Result.success(OperationResult.Error(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun reportSighting(petId: String): Result<OperationResult> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.reportSighting(ReportPetDto(petId))
            if (response.success) {
                Result.success(OperationResult.Success(response.message))
            } else {
                Result.success(OperationResult.Error(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}