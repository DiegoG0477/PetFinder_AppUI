package com.project.petfinder.sighting.data.repository

import android.net.Uri
import com.project.petfinder.core.data.storage.FileUploader
import com.project.petfinder.sighting.data.SightingApiService
import com.project.petfinder.sighting.data.model.ReportSightingRequest
import com.project.petfinder.sighting.domain.model.Sighting
import com.project.petfinder.sighting.domain.repository.SightingRepository
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
    ): Result<Sighting> = withContext(Dispatchers.IO) {
        try {
            val imageUrl = imageUri?.let { fileUploader.uploadFile(it) }

            val request = ReportSightingRequest(
                petId = petId,
                date = date,
                municipalityId = municipalityId,
                additionalInfo = additionalInfo,
                imageUrl = imageUrl
            )

            Result.success(apiService.reportSighting(request).toDomain())
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

    override suspend fun deleteSighting(id: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                Result.success(apiService.deleteSighting(id))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}