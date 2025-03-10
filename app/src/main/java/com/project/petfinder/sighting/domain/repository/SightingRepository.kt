package com.project.petfinder.sighting.domain.repository

import android.net.Uri
import com.project.petfinder.sighting.domain.model.Sighting
import org.threeten.bp.LocalDate

interface SightingRepository {
    suspend fun reportSighting(
        petId: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<Sighting>

    suspend fun getPetSightings(petId: String): Result<List<Sighting>>

    suspend fun getUserSightings(userId: String): Result<List<Sighting>>

    suspend fun getSighting(id: String): Result<Sighting>

    suspend fun deleteSighting(id: String): Result<Unit>
}