package com.project.petfinder.bulletin.data.model

data class CreateBulletinRequest(
    val petName: String,
    val date: String,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String?
)