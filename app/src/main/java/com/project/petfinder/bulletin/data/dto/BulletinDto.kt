package com.project.petfinder.bulletin.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BulletinDto(
    val id: String,
    val petName: String,
    val date: String,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String? = null // Can remain null
)