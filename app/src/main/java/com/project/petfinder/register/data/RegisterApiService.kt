package com.project.petfinder.register.data

import com.project.petfinder.register.data.model.RegisterRequest
import com.project.petfinder.register.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}