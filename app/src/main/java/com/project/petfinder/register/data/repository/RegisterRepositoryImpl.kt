package com.project.petfinder.register.data.repository

import com.project.petfinder.register.data.RegisterApiService
import com.project.petfinder.register.data.model.RegisterRequest
import com.project.petfinder.register.data.model.UserResponse
import com.project.petfinder.register.domain.model.RegisterResult
import com.project.petfinder.register.domain.model.User
import com.project.petfinder.register.domain.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepositoryImpl @Inject constructor(
    private val registerApiService: RegisterApiService
) : RegisterRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        municipalityId: String
    ): RegisterResult = withContext(Dispatchers.IO) {
        try {
            val request = RegisterRequest(
                name = name,
                email = email,
                password = password,
                municipalityId = municipalityId
            )
            val response = registerApiService.register(request)
            if (response.success) {
                RegisterResult(
                    isSuccess = true,
                    user = response.user?.toDomainUser()
                )
            } else {
                RegisterResult(
                    isSuccess = false,
                    errorMessage = response.message ?: "Registration failed"
                )
            }
        } catch (e: Exception) {
            RegisterResult(
                isSuccess = false,
                errorMessage = e.message ?: "Unknown error occurred"
            )
        }
    }

    private fun UserResponse.toDomainUser(): User {
        return User(
            id = id,
            name = name,
            email = email,
            municipalityId = municipalityId
        )
    }
}