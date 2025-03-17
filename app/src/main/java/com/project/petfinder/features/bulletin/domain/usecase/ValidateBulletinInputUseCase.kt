package com.project.petfinder.features.bulletin.domain.usecase

import android.net.Uri
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.model.OperationResult
import javax.inject.Inject

class ValidateBulletinInputUseCase @Inject constructor() {
    operator fun invoke(
        petName: String,
        selectedMunicipality: Municipality?,
        selectedImageUri: Uri?
    ): OperationResult {
        return when {
            petName.isBlank() -> OperationResult.Error("El nombre de la mascota es requerido")
            selectedMunicipality == null -> OperationResult.Error("Selecciona un municipio")
            selectedImageUri == null -> OperationResult.Error("La imagen es requerida")
            else -> OperationResult.Success("Validación exitosa")
        }
    }
}