package com.project.petfinder.register.data.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val municipalityId: String
)