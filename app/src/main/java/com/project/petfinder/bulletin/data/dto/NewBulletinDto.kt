package com.project.petfinder.bulletin.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewBulletinDto(
    val petName: String,
    val date: String,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String? = null
)