package com.project.petfinder.features.register.domain.repository

import com.project.petfinder.features.register.domain.model.RegisterResult

interface RegisterRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        municipalityId: String
    ): RegisterResult
}