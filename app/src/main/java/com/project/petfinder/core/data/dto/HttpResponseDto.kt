package com.project.petfinder.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class HttpResponseDto(
    val status: Int,
    val message: String,
    val success: Boolean
)