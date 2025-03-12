package com.project.petfinder.register.data.model

data class RegisterResponse(
    val success: Boolean,
    val message: String?,
    val user: UserResponse?
)