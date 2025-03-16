package com.project.petfinder.features.rescue.data.repository

import android.net.Uri
import com.project.petfinder.core.data.storage.FileUploader
import com.project.petfinder.features.rescue.data.remote.RescueApiService
import com.project.petfinder.features.rescue.data.dto.ReportRescueDto
import com.project.petfinder.features.rescue.data.mapper.toDomain
import com.project.petfinder.features.rescue.domain.model.Rescue
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.rescue.domain.repository.RescueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import javax.inject.Inject

class RescueRepositoryImpl @Inject constructor(
    private val apiService: RescueApiService,
    private val fileUploader: FileUploader
) : RescueRepository {

    override suspend fun reportRescue(
        petId: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<OperationResult> = withContext(Dispatchers.IO) {
        try {
            val imageUrl = imageUri?.let { fileUploader.uploadFile(it) }

            val request = ReportRescueDto(
                petId = petId,
                date = date.toString(),
                municipalityId = municipalityId,
                additionalInfo = additionalInfo,
                imageUrl = imageUrl
            )

            val response = apiService.reportRescue(request)
            if (response.success) {
                Result.success(OperationResult.Success(response.message))
            } else {
                Result.success(OperationResult.Error(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRescueById(rescueId: String): Result<Rescue> = withContext(Dispatchers.IO) {
        try {
            Result.success(apiService.getRescueById(rescueId).toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRescuesForPet(petId: String): Result<List<Rescue>> = withContext(Dispatchers.IO) {
        try {
            Result.success(apiService.getRescuesForPet(petId).map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRescuesByUser(userId: String): Result<List<Rescue>> = withContext(Dispatchers.IO) {
        try {
            Result.success(apiService.getRescuesByUser(userId).map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}