package com.project.petfinder.bulletin.data.remote

import com.project.petfinder.bulletin.data.dto.NewBulletinDto
import com.project.petfinder.bulletin.data.dto.BulletinDto
import com.project.petfinder.core.data.dto.HttpResponseDto
import retrofit2.http.*

interface BulletinApiService {
    @POST("bulletins")
    suspend fun createBulletin(@Body request: NewBulletinDto): HttpResponseDto

    @GET("bulletins")
    suspend fun getBulletins(): List<BulletinDto>

    @GET("bulletins/municipality/{municipalityId}")
    suspend fun getBulletinsByMunicipality(@Path("municipalityId") municipalityId: String): List<BulletinDto>

    @GET("bulletins/{id}")
    suspend fun getBulletin(@Path("id") id: String): BulletinDto

    @GET("bulletins/user/{userId}")
    suspend fun getUserBulletins(@Path("userId") userId: String): List<BulletinDto>

    @DELETE("bulletins/{id}")
    suspend fun deleteBulletin(@Path("id") id: String): HttpResponseDto

    @PUT("bulletins/{id}")
    suspend fun updateBulletin(
        @Path("id") id: String,
        @Body request: NewBulletinDto
    ): HttpResponseDto
}