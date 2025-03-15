package com.project.petfinder.login.data.repository

import com.project.petfinder.login.data.remote.AuthApiService
import com.project.petfinder.login.data.dto.LoginRequest
import com.project.petfinder.login.domain.model.AuthResult
import com.project.petfinder.login.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val authPreferences: AuthPreferences
) : AuthRepository {

    override suspend fun login(email: String, password: String): AuthResult = withContext(Dispatchers.IO) {
        try {
            val response = authApiService.login(LoginRequest(email, password))
            if (response.token.isNotEmpty()) {
                authPreferences.saveAuthToken(response.token)
                AuthResult.Success()  // or AuthResult.Success(user) if you have user data
            } else {
                AuthResult.Error("Invalid credentials")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Unknown error occurred")
        }
    }

    override suspend fun logout() = withContext(Dispatchers.IO) {
        try {
            authApiService.logout()
        } catch (e: Exception) {
            // Log error if needed
        } finally {
            authPreferences.clearAuthToken()
        }
    }

    override suspend fun isUserLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        authPreferences.getAuthToken().isNotEmpty()
    }
}