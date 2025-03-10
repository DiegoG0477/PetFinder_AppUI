package com.project.petfinder.sighting.domain.model

import org.threeten.bp.LocalDate

data class Sighting(
    val id: String = "",
    val petId: String,
    val date: LocalDate,
    val municipalityId: String,
    val municipalityName: String,
    val additionalInfo: String,
    val imageUrl: String,
    val userId: String,
    val createdAt: LocalDate = LocalDate.now()
)