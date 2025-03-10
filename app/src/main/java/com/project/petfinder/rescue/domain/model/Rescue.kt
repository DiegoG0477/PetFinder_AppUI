package com.project.petfinder.rescue.domain.model

import android.net.Uri
import org.threeten.bp.LocalDate

data class Rescue(
    val id: String,
    val petId: String,
    val date: LocalDate,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String,
    val reportedByUserId: String,
    val createdAt: LocalDate = LocalDate.now()
)

data class RescueRequest(
    val petId: String,
    val date: LocalDate,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUri: Uri?
)