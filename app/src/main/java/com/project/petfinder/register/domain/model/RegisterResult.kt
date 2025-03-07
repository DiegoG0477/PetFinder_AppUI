package com.project.petfinder.register.domain.model

data class RegisterResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null,
    val user: User? = null
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val municipalityId: String
)