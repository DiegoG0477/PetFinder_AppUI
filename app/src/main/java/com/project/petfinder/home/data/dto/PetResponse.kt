package com.project.petfinder.home.data.dto

data class PetResponse(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val ownerName: String?,
    val lastSeenLocation: String,
    val lastSeenDate: String?
)