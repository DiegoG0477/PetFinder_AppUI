package com.project.petfinder.features.sighting.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReportSightingDto(
    val petId: String,
    val date: String,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String?
)