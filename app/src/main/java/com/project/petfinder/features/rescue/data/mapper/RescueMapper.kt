package com.project.petfinder.features.rescue.data.mapper

import com.project.petfinder.features.rescue.data.dto.RescueDto
import com.project.petfinder.features.rescue.domain.model.Rescue
import org.threeten.bp.LocalDate

fun RescueDto.toDomain(): Rescue = Rescue(
    id = id,
    petId = petId,
    date = LocalDate.parse(date),
    municipalityId = municipalityId,
    additionalInfo = additionalInfo,
    imageUrl = imageUrl ?: "",
    reportedByUserId = reportedByUserId,
    createdAt = LocalDate.parse(createdAt)
)