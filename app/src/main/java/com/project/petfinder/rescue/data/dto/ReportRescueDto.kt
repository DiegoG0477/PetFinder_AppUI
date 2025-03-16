package com.project.petfinder.rescue.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReportRescueDto(
    val petId: String,
    val date: String,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String?
)