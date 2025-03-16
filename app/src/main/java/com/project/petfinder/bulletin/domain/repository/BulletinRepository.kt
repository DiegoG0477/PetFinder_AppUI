package com.project.petfinder.bulletin.domain.repository

import android.net.Uri
import com.project.petfinder.bulletin.domain.model.Bulletin
import com.project.petfinder.core.domain.model.OperationResult
import org.threeten.bp.LocalDate

interface BulletinRepository {
    suspend fun createBulletin(
        petName: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<OperationResult>

    suspend fun getBulletins(): Result<List<Bulletin>>

    suspend fun getBulletinsByMunicipality(municipalityId: String): Result<List<Bulletin>>

    suspend fun getBulletin(id: String): Result<Bulletin>

    suspend fun getUserBulletins(userId: String): Result<List<Bulletin>>

    suspend fun deleteBulletin(id: String): Result<OperationResult>

    suspend fun updateBulletin(
        id: String,
        petName: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<OperationResult>
}