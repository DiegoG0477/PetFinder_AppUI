package com.project.petfinder.login.data

import com.project.petfinder.login.data.model.LoginRequest
import com.project.petfinder.login.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/logout")
    suspend fun logout()
}