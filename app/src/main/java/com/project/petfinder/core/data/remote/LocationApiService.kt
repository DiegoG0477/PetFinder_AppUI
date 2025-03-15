package com.project.petfinder.core.data.remote

import com.project.petfinder.core.data.dto.MunicipalityResponse
import retrofit2.http.GET

interface LocationApiService {
    @GET("municipalities")
    suspend fun getMunicipalities(): List<MunicipalityResponse>
}