package com.project.petfinder.register.domain.repository

import com.project.petfinder.register.domain.model.RegisterResult

interface RegisterRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        municipalityId: String
    ): RegisterResult
}