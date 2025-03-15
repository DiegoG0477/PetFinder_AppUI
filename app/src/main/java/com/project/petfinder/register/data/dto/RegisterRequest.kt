package com.project.petfinder.register.data.dto

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val municipalityId: String
)