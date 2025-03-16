package com.project.petfinder.register.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val municipalityId: String
)