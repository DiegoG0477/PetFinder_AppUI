package com.project.petfinder.bulletin.data.dto

data class CreateBulletinRequest(
    val petName: String,
    val date: String,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String?
)