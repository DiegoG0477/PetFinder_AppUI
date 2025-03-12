package com.project.petfinder.bulletin.data

import com.project.petfinder.bulletin.data.model.BulletinResponse
import com.project.petfinder.bulletin.data.model.CreateBulletinRequest
import retrofit2.http.*

interface BulletinApiService {
    @POST("bulletins")
    suspend fun createBulletin(@Body request: CreateBulletinRequest): BulletinResponse

    @GET("bulletins")
    suspend fun getBulletins(): List<BulletinResponse>

    @GET("bulletins/municipality/{municipalityId}")
    suspend fun getBulletinsByMunicipality(@Path("municipalityId") municipalityId: String): List<BulletinResponse>

    @GET("bulletins/{id}")
    suspend fun getBulletin(@Path("id") id: String): BulletinResponse

    @GET("bulletins/user/{userId}")
    suspend fun getUserBulletins(@Path("userId") userId: String): List<BulletinResponse>

    @DELETE("bulletins/{id}")
    suspend fun deleteBulletin(@Path("id") id: String)

    @PUT("bulletins/{id}")
    suspend fun updateBulletin(
        @Path("id") id: String,
        @Body request: CreateBulletinRequest
    ): BulletinResponse
}