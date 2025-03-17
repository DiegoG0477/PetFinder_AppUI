package com.project.petfinder.features.login.domain.usecase

import com.project.petfinder.core.domain.model.OperationResult
import javax.inject.Inject

class ValidateLoginCredentialsUseCase @Inject constructor() {
    operator fun invoke(email: String, password: String): OperationResult {
        if (email.isBlank()) {
            return OperationResult.Error("El correo electrónico es requerido")
        }

        if (!isValidEmail(email)) {
            return OperationResult.Error("El correo electrónico no es válido")
        }

        if (password.isBlank()) {
            return OperationResult.Error("La contraseña es requerida")
        }

        if (password.length < 6) {
            return OperationResult.Error("La contraseña debe tener al menos 6 caracteres")
        }

        return OperationResult.Success("Credenciales válidas")
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}