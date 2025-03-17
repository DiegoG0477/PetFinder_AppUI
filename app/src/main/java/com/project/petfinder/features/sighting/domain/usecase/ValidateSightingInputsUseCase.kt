package com.project.petfinder.features.sighting.domain.usecase

import android.net.Uri
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.model.OperationResult
import org.threeten.bp.LocalDate
import javax.inject.Inject

class ValidateSightingInputsUseCase @Inject constructor() {
    operator fun invoke(
        date: LocalDate,
        selectedMunicipality: Municipality?,
        imageUri: Uri?,
        additionalInfo: String
    ): OperationResult {
        return when {
            date.isAfter(LocalDate.now()) ->
                OperationResult.Error("La fecha no puede ser posterior a hoy")

            selectedMunicipality == null ->
                OperationResult.Error("Selecciona un municipio")

            imageUri == null ->
                OperationResult.Error("La imagen es requerida")

            additionalInfo.isBlank() ->
                OperationResult.Error("La información adicional es requerida")

            additionalInfo.length < 10 ->
                OperationResult.Error("La descripción debe ser más detallada")

            else -> OperationResult.Success("Validación exitosa")
        }
    }
}