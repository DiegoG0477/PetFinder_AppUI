package com.project.petfinder.sighting.data

import com.project.petfinder.sighting.data.model.ReportSightingRequest
import com.project.petfinder.sighting.data.model.SightingResponse
import retrofit2.http.*

interface SightingApiService {
    @POST("sightings")
    suspend fun reportSighting(@Body request: ReportSightingRequest): SightingResponse

    @GET("pets/{petId}/sightings")
    suspend fun getPetSightings(@Path("petId") petId: String): List<SightingResponse>

    @GET("users/{userId}/sightings")
    suspend fun getUserSightings(@Path("userId") userId: String): List<SightingResponse>

    @GET("sightings/{id}")
    suspend fun getSighting(@Path("id") id: String): SightingResponse

    @DELETE("sightings/{id}")
    suspend fun deleteSighting(@Path("id") id: String)
}