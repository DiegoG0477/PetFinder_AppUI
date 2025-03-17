package com.project.petfinder.features.rescue.domain.usecase

import android.net.Uri
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.rescue.domain.repository.RescueRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

class ReportRescueUseCase @Inject constructor(
    private val rescueRepository: RescueRepository
) {
    suspend operator fun invoke(
        petId: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri?
    ): Result<OperationResult> {
        return rescueRepository.reportRescue(
            petId = petId,
            date = date,
            municipalityId = municipalityId,
            additionalInfo = additionalInfo,
            imageUri = imageUri
        )
    }
}