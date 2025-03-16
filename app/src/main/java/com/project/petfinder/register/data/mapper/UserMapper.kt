package com.project.petfinder.register.data.mapper

import com.project.petfinder.register.data.dto.UserResponse
import com.project.petfinder.register.domain.model.User

fun UserResponse.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        municipalityId = municipalityId
    )
}