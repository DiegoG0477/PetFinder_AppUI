package com.project.petfinder.features.sighting.data.repository

import android.net.Uri
import com.project.petfinder.core.data.storage.FileUploader
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.sighting.data.remote.SightingApiService
import com.project.petfinder.features.sighting.data.dto.ReportSightingDto
import com.project.petfinder.features.sighting.data.mapper.toDomain
import com.project.petfinder.features.sighting.domain.model.Sighting
import com.project.petfinder.features.sighting.domain.repository.SightingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import javax.inject.Inject

class SightingRepositoryImpl @Inject constructor(
    private val apiService: SightingApiService,
    private val fileUploader: FileUploader
) : SightingRepository {

    override suspend fun reportSighting(
        petId: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<OperationResult> = withContext(Dispatchers.IO) {
        try {
            val imageUrl = imageUri?.let { fileUploader.uploadFile(it) }

            val request = ReportSightingDto(
                petId = petId,
                date = date.toString(),
                municipalityId = municipalityId,
                additionalInfo = additionalInfo,
                imageUrl = imageUrl
            )

            val response = apiService.reportSighting(request)
            if (response.success) {
                Result.success(OperationResult.Success(response.message))
            } else {
                Result.success(OperationResult.Error(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPetSightings(petId: String): Result<List<Sighting>> =
        withContext(Dispatchers.IO) {
            try {
                Result.success(apiService.getPetSightings(petId).map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun getUserSightings(userId: String): Result<List<Sighting>> =
        withContext(Dispatchers.IO) {
            try {
                Result.success(apiService.getUserSightings(userId).map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun getSighting(id: String): Result<Sighting> =
        withContext(Dispatchers.IO) {
            try {
                Result.success(apiService.getSighting(id).toDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun deleteSighting(id: String): Result<OperationResult> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteSighting(id)
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