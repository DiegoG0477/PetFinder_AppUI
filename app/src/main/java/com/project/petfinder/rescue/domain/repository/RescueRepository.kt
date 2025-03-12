package com.project.petfinder.rescue.domain.repository

import android.net.Uri
import com.project.petfinder.rescue.domain.model.Rescue
import org.threeten.bp.LocalDate

interface RescueRepository {
    suspend fun reportRescue(
        petId: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Rescue

    suspend fun getRescueById(rescueId: String): Rescue

    suspend fun getRescuesForPet(petId: String): List<Rescue>

    suspend fun getRescuesByUser(userId: String): List<Rescue>
}