package com.project.petfinder.register.data.dto

data class RegisterResponse(
    val success: Boolean,
    val message: String?,
    val user: UserResponse?
)