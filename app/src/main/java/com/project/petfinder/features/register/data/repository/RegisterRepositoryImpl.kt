package com.project.petfinder.features.register.data.repository

import com.project.petfinder.features.register.data.remote.RegisterApiService
import com.project.petfinder.features.register.data.dto.RegisterRequestDto
import com.project.petfinder.features.register.data.mapper.toDomain
import com.project.petfinder.features.register.domain.model.RegisterResult
import com.project.petfinder.features.register.domain.repository.RegisterRepository
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
            val request = RegisterRequestDto(
                name = name,
                email = email,
                password = password,
                municipalityId = municipalityId
            )
            val response = registerApiService.register(request)
            if (response.success) {
                RegisterResult(
                    isSuccess = true,
                    user = response.user?.toDomain()
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
}