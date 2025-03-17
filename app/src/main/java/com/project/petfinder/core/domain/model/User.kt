package com.project.petfinder.core.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val municipalityId: String
)