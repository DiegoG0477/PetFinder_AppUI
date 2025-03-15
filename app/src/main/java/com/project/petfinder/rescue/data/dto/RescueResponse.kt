package com.project.petfinder.rescue.data.dto

import com.project.petfinder.rescue.domain.model.Rescue
import org.threeten.bp.LocalDate

data class RescueResponse(
    val id: String,
    val petId: String,
    val date: String,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String?,
    val reportedByUserId: String,
    val createdAt: String
) {
    fun toDomain(): Rescue = Rescue(
        id = id,
        petId = petId,
        date = LocalDate.parse(date),
        municipalityId = municipalityId,
        additionalInfo = additionalInfo,
        imageUrl = imageUrl ?: "",
        reportedByUserId = reportedByUserId,
        createdAt = LocalDate.parse(createdAt)
    )
}