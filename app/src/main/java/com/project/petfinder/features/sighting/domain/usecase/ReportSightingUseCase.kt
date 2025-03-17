package com.project.petfinder.features.sighting.domain.usecase

import android.net.Uri
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.sighting.domain.repository.SightingRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

class ReportSightingUseCase @Inject constructor(
    private val sightingRepository: SightingRepository
) {
    suspend operator fun invoke(
        petId: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<OperationResult> {
        return sightingRepository.reportSighting(
            petId = petId,
            date = date,
            municipalityId = municipalityId,
            additionalInfo = additionalInfo,
            imageUri = imageUri
        )
    }
}