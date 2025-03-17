package com.project.petfinder.features.login.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.petfinder.features.login.presentation.viewmodel.LoginUiState

@Preview(showBackground = true)
@Composable
fun LoginScreenEmptyPreview() {
    LoginContent(
        uiState = LoginUiState(),
        onEmailChanged = {},
        onPasswordChanged = {},
        onLoginClick = {},
        onRegisterClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenLoadingPreview() {
    LoginContent(
        uiState = LoginUiState(isLoading = true),
        onEmailChanged = {},
        onPasswordChanged = {},
        onLoginClick = {},
        onRegisterClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenErrorPreview() {
    LoginContent(
        uiState = LoginUiState(
            email = "test@example.com",
            password = "password123",
            errorMessage = "Credenciales inválidas"
        ),
        onEmailChanged = {},
        onPasswordChanged = {},
        onLoginClick = {},
        onRegisterClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenFilledPreview() {
    LoginContent(
        uiState = LoginUiState(
            email = "test@example.com",
            password = "password123"
        ),
        onEmailChanged = {},
        onPasswordChanged = {},
        onLoginClick = {},
        onRegisterClick = {}
    )
}