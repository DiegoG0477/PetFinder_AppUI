package com.project.petfinder.register.data.dto

import com.project.petfinder.register.domain.model.User

//@Serializable
data class RegisterDto(
    val id: String,
    val name: String,
    val email: String,
    val municipalityId: String
) {
    fun toDomain(): User = User(
        id = id,
        name = name,
        email = email,
        municipalityId = municipalityId
    )
}

//@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val municipalityId: String
)

//@Serializable
data class RegisterResponse(
    val success: Boolean,
    val message: String? = null,
    val user: RegisterDto? = null
)