package com.project.petfinder.core.data

import com.project.petfinder.core.data.model.MunicipalityResponse
import retrofit2.http.GET

interface LocationApiService {
    @GET("municipalities")
    suspend fun getMunicipalities(): List<MunicipalityResponse>
}