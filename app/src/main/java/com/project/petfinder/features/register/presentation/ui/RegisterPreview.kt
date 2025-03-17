package com.project.petfinder.features.register.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.features.register.presentation.viewmodel.RegisterUiState

@Preview(showBackground = true)
@Composable
fun RegisterScreenEmptyPreview() {
    RegisterContent(
        uiState = RegisterUiState(),
        onNameChanged = {},
        onEmailChanged = {},
        onPasswordChanged = {},
        onMunicipalitySelected = {},
        onRegisterClick = {},
        onLoginClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenLoadingPreview() {
    RegisterContent(
        uiState = RegisterUiState(isLoading = true),
        onNameChanged = {},
        onEmailChanged = {},
        onPasswordChanged = {},
        onMunicipalitySelected = {},
        onRegisterClick = {},
        onLoginClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenErrorPreview() {
    RegisterContent(
        uiState = RegisterUiState(
            errorMessage = "Ha ocurrido un error al registrar la cuenta"
        ),
        onNameChanged = {},
        onEmailChanged = {},
        onPasswordChanged = {},
        onMunicipalitySelected = {},
        onRegisterClick = {},
        onLoginClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenFilledPreview() {
    val sampleMunicipality = Municipality(
        id = 1,
        name = "Guadalajara"
    )

    RegisterContent(
        uiState = RegisterUiState(
            name = "John Doe",
            email = "john@example.com",
            password = "password123",
            selectedMunicipality = sampleMunicipality,
            municipalities = listOf(sampleMunicipality)
        ),
        onNameChanged = {},
        onEmailChanged = {},
        onPasswordChanged = {},
        onMunicipalitySelected = {},
        onRegisterClick = {},
        onLoginClick = {}
    )
}