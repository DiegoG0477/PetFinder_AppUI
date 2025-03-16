package com.project.petfinder.features.register.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val success: Boolean,
    val message: String?,
    val user: UserResponse? = null
)