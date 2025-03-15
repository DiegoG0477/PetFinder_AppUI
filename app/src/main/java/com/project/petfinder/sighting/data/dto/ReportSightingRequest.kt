package com.project.petfinder.sighting.data.dto

import org.threeten.bp.LocalDate

data class ReportSightingRequest(
    val petId: String,
    val date: LocalDate,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String?
)