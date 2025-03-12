package com.project.petfinder.home.data.model

data class PetResponse(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val ownerName: String?,
    val lastSeenLocation: String,
    val lastSeenDate: String?
)