package com.project.petfinder.features.login.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.petfinder.core.ui.component.AppLogo
import com.project.petfinder.features.login.presentation.ui.component.LoginForm
import com.project.petfinder.features.login.presentation.ui.component.LoginHeader
import com.project.petfinder.features.login.presentation.ui.component.RegisterPrompt
import com.project.petfinder.features.login.presentation.viewmodel.LoginUiState

@Composable
fun LoginContent(
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        AppLogo() // Este componente debería moverse al core ya que se usa en múltiples pantallas

        Spacer(modifier = Modifier.height(60.dp))

        // Login Header
        LoginHeader()

        // Login Form
        LoginForm(
            email = uiState.email,
            password = uiState.password,
            isLoading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onLoginClick = onLoginClick
        )

        Spacer(modifier = Modifier.weight(1f))

        // Register Link
        RegisterPrompt(
            onRegisterClick = onRegisterClick
        )
    }
}