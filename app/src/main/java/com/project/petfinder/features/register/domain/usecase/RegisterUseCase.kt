package com.project.petfinder.features.register.domain.usecase

import com.project.petfinder.features.register.domain.model.RegisterResult
import com.project.petfinder.features.register.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        municipalityId: String
    ): RegisterResult {
        return registerRepository.register(
            name = name,
            email = email,
            password = password,
            municipalityId = municipalityId
        )
    }
}