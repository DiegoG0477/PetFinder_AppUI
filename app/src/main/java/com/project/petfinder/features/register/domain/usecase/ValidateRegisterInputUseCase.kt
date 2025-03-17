package com.project.petfinder.features.register.domain.usecase

import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.model.OperationResult
import javax.inject.Inject

class ValidateRegisterInputsUseCase @Inject constructor() {
    operator fun invoke(
        name: String,
        email: String,
        password: String,
        selectedMunicipality: Municipality?
    ): OperationResult {
        return when {
            name.isBlank() ->
                OperationResult.Error("El nombre es requerido")

            email.isBlank() ->
                OperationResult.Error("El correo electrónico es requerido")

            !isValidEmail(email) ->
                OperationResult.Error("El correo electrónico no es válido")

            selectedMunicipality == null ->
                OperationResult.Error("Selecciona un municipio")

            password.isBlank() ->
                OperationResult.Error("La contraseña es requerida")

            password.length < 6 ->
                OperationResult.Error("La contraseña debe tener al menos 6 caracteres")

            else -> OperationResult.Success("Validación exitosa")
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}