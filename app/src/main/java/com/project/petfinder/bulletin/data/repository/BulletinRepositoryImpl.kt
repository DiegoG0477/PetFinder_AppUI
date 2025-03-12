package com.project.petfinder.bulletin.data.repository

import android.net.Uri
import com.project.petfinder.bulletin.data.BulletinApiService
import com.project.petfinder.bulletin.data.model.CreateBulletinRequest
import com.project.petfinder.bulletin.data.model.BulletinResponse
import com.project.petfinder.bulletin.domain.model.Bulletin
import com.project.petfinder.bulletin.domain.repository.BulletinRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BulletinRepositoryImpl @Inject constructor(
    private val apiService: BulletinApiService
) : BulletinRepository {

    override suspend fun createBulletin(
        petName: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<Bulletin> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.createBulletin(
                CreateBulletinRequest(
                    petName = petName,
                    date = date.toString(),
                    municipalityId = municipalityId,
                    additionalInfo = additionalInfo,
                    imageUrl = imageUri?.toString()
                )
            )
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBulletins(): Result<List<Bulletin>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getBulletins()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBulletinsByMunicipality(municipalityId: String): Result<List<Bulletin>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getBulletinsByMunicipality(municipalityId)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBulletin(id: String): Result<Bulletin> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getBulletin(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserBulletins(userId: String): Result<List<Bulletin>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getUserBulletins(userId)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteBulletin(id: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            apiService.deleteBulletin(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateBulletin(
        id: String,
        petName: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<Bulletin> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.updateBulletin(
                id,
                CreateBulletinRequest(
                    petName = petName,
                    date = date.toString(),
                    municipalityId = municipalityId,
                    additionalInfo = additionalInfo,
                    imageUrl = imageUri?.toString()
                )
            )
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun BulletinResponse.toDomain(): Bulletin = Bulletin(
        id = this.id,
        petName = this.petName,
        date = LocalDate.parse(this.date),
        municipalityId = this.municipalityId,
        municipalityName = "",
        additionalInfo = this.additionalInfo,
        imageUrl = this.imageUrl?.let { Uri.parse(it) }.toString(),
        userId = ""
    )
}