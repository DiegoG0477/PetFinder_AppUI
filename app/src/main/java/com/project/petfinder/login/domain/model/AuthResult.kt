package com.project.petfinder.login.domain.model

data class AuthResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null,
    val user: User? = null
)