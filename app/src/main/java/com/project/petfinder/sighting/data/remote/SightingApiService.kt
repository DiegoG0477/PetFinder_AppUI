package com.project.petfinder.sighting.data.remote

import com.project.petfinder.core.data.dto.HttpResponseDto
import com.project.petfinder.sighting.data.dto.ReportSightingDto
import com.project.petfinder.sighting.data.dto.SightingDto
import retrofit2.http.*

interface SightingApiService {
    @POST("sightings")
    suspend fun reportSighting(@Body request: ReportSightingDto): HttpResponseDto

    @GET("pets/{petId}/sightings")
    suspend fun getPetSightings(@Path("petId") petId: String): List<SightingDto>

    @GET("users/{userId}/sightings")
    suspend fun getUserSightings(@Path("userId") userId: String): List<SightingDto>

    @GET("sightings/{id}")
    suspend fun getSighting(@Path("id") id: String): SightingDto

    @DELETE("sightings/{id}")
    suspend fun deleteSighting(@Path("id") id: String): HttpResponseDto
}