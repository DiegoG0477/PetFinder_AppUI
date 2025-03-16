package com.project.petfinder.features.home.domain.model

data class Pet(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val ownerName: String,
    val lastSeenLocation: String,
    val lastSeenDate: String
)