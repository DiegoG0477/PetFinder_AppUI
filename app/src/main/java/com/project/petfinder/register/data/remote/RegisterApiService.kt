package com.project.petfinder.register.data.remote

import com.project.petfinder.register.data.dto.RegisterRequest
import com.project.petfinder.register.data.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}