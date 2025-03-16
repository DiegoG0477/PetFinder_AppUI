package com.project.petfinder.login.data.remote

import com.project.petfinder.login.data.dto.LoginRequestDto
import com.project.petfinder.login.data.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

    @POST("auth/logout")
    suspend fun logout()
}