package com.project.petfinder.features.login.domain.model

import com.project.petfinder.core.domain.model.User

sealed class AuthResult {
    data class Success(val user: User? = null) : AuthResult()
    data class Error(val errorMessage: String) : AuthResult()
}