package com.project.petfinder.features.bulletin.domain.usecase

import android.net.Uri
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.bulletin.domain.repository.BulletinRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

class CreateBulletinUseCase @Inject constructor(
    private val bulletinRepository: BulletinRepository
) {
    suspend operator fun invoke(
        petName: String,
        date: LocalDate,
        municipalityId: String,
        additionalInfo: String,
        imageUri: Uri
    ): Result<OperationResult> {
        return bulletinRepository.createBulletin(
            petName = petName,
            date = date,
            municipalityId = municipalityId,
            additionalInfo = additionalInfo,
            imageUri = imageUri
        )
    }
}