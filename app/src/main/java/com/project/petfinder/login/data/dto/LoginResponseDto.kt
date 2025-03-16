package com.project.petfinder.login.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String,
    val userId: String
)