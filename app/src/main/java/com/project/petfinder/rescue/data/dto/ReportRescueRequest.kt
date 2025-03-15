package com.project.petfinder.rescue.data.dto

import org.threeten.bp.LocalDate

data class ReportRescueRequest(
    val petId: String,
    val date: LocalDate,
    val municipalityId: String,
    val additionalInfo: String,
    val imageUrl: String?
)