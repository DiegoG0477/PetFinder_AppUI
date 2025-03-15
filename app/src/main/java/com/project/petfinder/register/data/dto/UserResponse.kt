package com.project.petfinder.register.data.dto

data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val municipalityId: String
)