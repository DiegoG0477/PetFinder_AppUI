package com.project.petfinder.features.register.domain.model

import com.project.petfinder.core.domain.model.User

data class RegisterResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null,
    val user: User? = null
)