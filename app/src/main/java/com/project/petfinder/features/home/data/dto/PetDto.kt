package com.project.petfinder.features.home.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PetDto(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val ownerName: String?,
    val lastSeenLocation: String,
    val lastSeenDate: String?
)