package com.project.petfinder.features.bulletin.data.repository

import android.net.Uri
import com.project.petfinder.features.bulletin.data.remote.BulletinApiService
import com.project.petfinder.features.bulletin.data.dto.NewBulletinDto
import com.project.petfinder.features.bulletin.data.mapper.toDomain
import com.project.petfinder.features.bulletin.domain.model.Bulletin
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.bulletin.domain.repository.BulletinRepository
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
    ): Result<OperationResult> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.createBulletin(
                NewBulletinDto(
                    petName = petName,
                    date = date.toString(),
                    municipalityId = municipalityId,
                    additionalInfo = additionalInfo,
                    imageUrl = imageUri?.toString()
                )
            )
            if (response.success) {
                Result.success(OperationResult.Success(response.message))
            } else {
                Result.success(OperationResult.Error(response.message))
            }
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

    override suspend fun deleteBulletin(id: String): Result<OperationResult> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.deleteBulletin(id)
            if (response.success) {
                Result.success(OperationResult.Success(response.message))
            } else {
                Result.success(OperationResult.Error(response.message))
            }
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
    ): Result<OperationResult> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.updateBulletin(
                id,
                NewBulletinDto(
                    petName = petName,
                    date = date.toString(),
                    municipalityId = municipalityId,
                    additionalInfo = additionalInfo,
                    imageUrl = imageUri?.toString()
                )
            )
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