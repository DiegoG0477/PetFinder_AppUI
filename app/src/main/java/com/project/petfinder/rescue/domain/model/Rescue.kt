package com.project.petfinder.rescue.domain.model

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