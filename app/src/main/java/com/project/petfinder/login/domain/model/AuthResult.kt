package com.project.petfinder.login.domain.model

sealed class AuthResult {
    data class Success(val user: User? = null) : AuthResult()
    data class Error(val errorMessage: String) : AuthResult()
}