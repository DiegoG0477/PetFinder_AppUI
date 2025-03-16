package com.project.petfinder.features.register.data.remote

import com.project.petfinder.features.register.data.dto.RegisterRequestDto
import com.project.petfinder.features.register.data.dto.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequestDto): RegisterResponseDto
}