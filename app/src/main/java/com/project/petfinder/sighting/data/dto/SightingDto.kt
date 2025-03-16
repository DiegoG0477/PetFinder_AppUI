package com.project.petfinder.sighting.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SightingDto(
    val id: String,
    val petId: String,
    val date: String,
    val municipalityId: String,
    val municipalityName: String,
    val additionalInfo: String,
    val imageUrl: String,
    val userId: String,
    val createdAt: String
)