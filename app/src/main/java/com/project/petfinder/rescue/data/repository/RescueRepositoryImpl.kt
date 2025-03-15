package com.project.petfinder.rescue.data.repository

import android.net.Uri
import com.project.petfinder.core.data.storage.FileUploader
import com.project.petfinder.rescue.data.remote.RescueApiService
import com.project.petfinder.rescue.data.dto.ReportRescueRequest
import com.project.petfinder.rescue.domain.model.Rescue
import com.project.petfinder.rescue.domain.repository.RescueRepository
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
    ): Rescue = withContext(Dispatchers.IO) {
        try {
            val imageUrl = imageUri?.let { fileUploader.uploadFile(it) }

            val request = ReportRescueRequest(
                petId = petId,
                date = date,
                municipalityId = municipalityId,
                additionalInfo = additionalInfo,
                imageUrl = imageUrl
            )

            apiService.reportRescue(request).toDomain()
        } catch (e: Exception) {
            throw e // O manejar el error según se requiera
        }
    }

    override suspend fun getRescueById(rescueId: String): Rescue = withContext(Dispatchers.IO) {
        try {
            apiService.getRescueById(rescueId).toDomain()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRescuesForPet(petId: String): List<Rescue> = withContext(Dispatchers.IO) {
        try {
            apiService.getRescuesForPet(petId).map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getRescuesByUser(userId: String): List<Rescue> = withContext(Dispatchers.IO) {
        try {
            apiService.getRescuesByUser(userId).map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}