package com.project.petfinder.features.login.domain.usecase

import com.project.petfinder.features.login.domain.model.AuthResult
import com.project.petfinder.features.login.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        return authRepository.login(email, password)
    }
}