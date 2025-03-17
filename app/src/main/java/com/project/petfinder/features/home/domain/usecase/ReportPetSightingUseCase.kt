package com.project.petfinder.features.home.domain.usecase

import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.home.domain.repository.PetRepository
import javax.inject.Inject

class ReportPetSightingUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(petId: String): Result<OperationResult> {
        return petRepository.reportSighting(petId)
    }
}