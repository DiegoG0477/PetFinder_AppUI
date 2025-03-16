package com.project.petfinder.features.bulletin.domain.model

import org.threeten.bp.LocalDate

data class Bulletin(
    val id: String = "",
    val petName: String,
    val date: LocalDate,
    val municipalityId: String,
    val municipalityName: String,
    val additionalInfo: String,
    val imageUrl: String,
    val userId: String,
    val createdAt: LocalDate = LocalDate.now()
)