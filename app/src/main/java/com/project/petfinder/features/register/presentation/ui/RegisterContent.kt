package com.project.petfinder.features.register.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.ui.component.AppLogo
import com.project.petfinder.features.register.presentation.ui.component.RegisterForm
import com.project.petfinder.features.register.presentation.ui.component.RegisterHeader
import com.project.petfinder.features.register.presentation.ui.component.LoginPrompt
import com.project.petfinder.features.register.presentation.viewmodel.RegisterUiState

@Composable
fun RegisterContent(
    uiState: RegisterUiState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onMunicipalitySelected: (Municipality) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        AppLogo(
            modifier = Modifier.padding(bottom = 40.dp)
        )

        RegisterHeader()

        RegisterForm(
            uiState = uiState,
            onNameChanged = onNameChanged,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onMunicipalitySelected = onMunicipalitySelected,
            onRegisterClick = onRegisterClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        LoginPrompt(
            onLoginClick = onLoginClick
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}