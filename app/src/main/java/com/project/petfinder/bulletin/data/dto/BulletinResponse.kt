package com.project.petfinder.bulletin.data.dto

data class BulletinResponse(
    val id: String,
    val petName: String,
    val date: String,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String?
)