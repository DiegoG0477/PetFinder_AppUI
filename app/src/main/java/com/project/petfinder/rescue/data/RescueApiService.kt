package com.project.petfinder.rescue.data

import com.project.petfinder.rescue.data.model.ReportRescueRequest
import com.project.petfinder.rescue.data.model.RescueResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RescueApiService {
    @POST("rescues")
    suspend fun reportRescue(@Body request: ReportRescueRequest): RescueResponse

    @GET("rescues/{rescueId}")
    suspend fun getRescueById(@Path("rescueId") rescueId: String): RescueResponse

    @GET("pets/{petId}/rescues")
    suspend fun getRescuesForPet(@Path("petId") petId: String): List<RescueResponse>

    @GET("users/{userId}/rescues")
    suspend fun getRescuesByUser(@Path("userId") userId: String): List<RescueResponse>
}