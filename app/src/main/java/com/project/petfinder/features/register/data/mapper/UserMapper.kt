package com.project.petfinder.features.register.data.mapper

import com.project.petfinder.features.register.data.dto.UserResponse
import com.project.petfinder.core.domain.model.User

fun UserResponse.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        municipalityId = municipalityId
    )
}