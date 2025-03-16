package com.project.petfinder.home.data.remote

import com.project.petfinder.home.data.dto.PetDto
import com.project.petfinder.home.data.dto.ReportPetDto
import com.project.petfinder.core.data.dto.HttpResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PetApiService {
    @GET("pets/lost")
    suspend fun getLostPets(): List<PetDto>

    @POST("pets/rescue")
    suspend fun reportRescue(@Body request: ReportPetDto): HttpResponseDto

    @POST("pets/sighting")
    suspend fun reportSighting(@Body request: ReportPetDto): HttpResponseDto
}