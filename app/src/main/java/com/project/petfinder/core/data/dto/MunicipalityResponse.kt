package com.project.petfinder.core.data.dto

import com.project.petfinder.core.domain.model.Municipality

data class MunicipalityResponse(
    val id: Int,
    val name: String
) {
    fun toDomain(): Municipality = Municipality(
        id = id,
        name = name
    )
}