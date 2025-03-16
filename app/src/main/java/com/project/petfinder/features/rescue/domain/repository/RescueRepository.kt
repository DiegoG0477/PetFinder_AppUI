package com.project.petfinder.features.rescue.domain.repository

import android.net.Uri
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.rescue.domain.model.Rescue
import org.threeten.bp.LocalDate

interface RescueRepository {
    suspend fun reportRescue(
        petId: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<OperationResult>

    suspend fun getRescueById(rescueId: String): Result<Rescue>

    suspend fun getRescuesForPet(petId: String): Result<List<Rescue>>

    suspend fun getRescuesByUser(userId: String): Result<List<Rescue>>
}