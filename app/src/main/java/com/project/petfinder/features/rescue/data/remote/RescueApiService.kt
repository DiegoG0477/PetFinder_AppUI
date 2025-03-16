package com.project.petfinder.features.rescue.data.remote

import com.project.petfinder.core.data.dto.HttpResponseDto
import com.project.petfinder.features.rescue.data.dto.ReportRescueDto
import com.project.petfinder.features.rescue.data.dto.RescueDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RescueApiService {
    @POST("rescues")
    suspend fun reportRescue(@Body request: ReportRescueDto): HttpResponseDto

    @GET("rescues/{rescueId}")
    suspend fun getRescueById(@Path("rescueId") rescueId: String): RescueDto

    @GET("pets/{petId}/rescues")
    suspend fun getRescuesForPet(@Path("petId") petId: String): List<RescueDto>

    @GET("users/{userId}/rescues")
    suspend fun getRescuesByUser(@Path("userId") userId: String): List<RescueDto>
}