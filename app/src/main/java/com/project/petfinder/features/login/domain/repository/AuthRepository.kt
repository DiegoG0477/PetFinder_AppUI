package com.project.petfinder.features.login.domain.repository

import com.project.petfinder.features.login.domain.model.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
}