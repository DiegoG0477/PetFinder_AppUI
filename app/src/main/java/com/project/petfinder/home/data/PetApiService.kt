package com.project.petfinder.home.data

import com.project.petfinder.home.data.model.PetResponse
import com.project.petfinder.home.data.model.ReportPetRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PetApiService {
    @GET("pets/lost")
    suspend fun getLostPets(): List<PetResponse>

    @POST("pets/rescue")
    suspend fun reportRescue(@Body request: ReportPetRequest)

    @POST("pets/sighting")
    suspend fun reportSighting(@Body request: ReportPetRequest)
}