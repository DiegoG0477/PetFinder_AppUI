package com.project.petfinder.features.sighting.data.mapper

import com.project.petfinder.features.sighting.data.dto.SightingDto
import com.project.petfinder.features.sighting.domain.model.Sighting
import org.threeten.bp.LocalDate

fun SightingDto.toDomain(): Sighting = Sighting(
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