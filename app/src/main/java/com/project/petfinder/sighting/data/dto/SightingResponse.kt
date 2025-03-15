package com.project.petfinder.sighting.data.dto

import com.project.petfinder.sighting.domain.model.Sighting
import org.threeten.bp.LocalDate

data class SightingResponse(
    val id: String,
    val petId: String,
    val date: String,
    val municipalityId: String,
    val municipalityName: String,
    val additionalInfo: String,
    val imageUrl: String,
    val userId: String,
    val createdAt: String
) {
    fun toDomain(): Sighting = Sighting(
        id = id,
        petId = petId,
        date = LocalDate.parse(date),
        municipalityId = municipalityId,
        municipalityName = municipalityName,
        additionalInfo = additionalInfo,
        imageUrl = imageUrl,
        userId = userId,
        createdAt = LocalDate.parse(createdAt)
    )
}