package com.project.petfinder.features.register.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Eye
import com.composables.icons.lucide.EyeOff
import com.composables.icons.lucide.Lock
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.composables.icons.lucide.User
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.ui.component.FormField
import com.project.petfinder.core.ui.component.MunicipalityDropdown
import com.project.petfinder.core.ui.component.PrimaryButton
import com.project.petfinder.features.register.presentation.viewmodel.RegisterUiState

@Composable
fun RegisterForm(
    uiState: RegisterUiState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onMunicipalitySelected: (Municipality) -> Unit,
    onRegisterClick: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    if (uiState.errorMessage != null) {
        Text(
            text = uiState.errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }

    FormField(
        label = "Nombre",
        value = uiState.name,
        onValueChange = onNameChanged,
        placeholder = "Jhon Doe",
        leadingIcon = {
            Icon(
                Lucide.User,
                contentDescription = "Name",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    FormField(
        label = "Correo Electrónico",
        value = uiState.email,
        onValueChange = onEmailChanged,
        placeholder = "jhon@doe.com",
        leadingIcon = {
            Icon(
                Lucide.Mail,
                contentDescription = "Email",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        keyboardType = KeyboardType.Email
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Municipio",
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium
        ),
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )

    MunicipalityDropdown(
        selectedMunicipality = uiState.selectedMunicipality,
        municipalities = uiState.municipalities,
        onMunicipalitySelected = onMunicipalitySelected,
        enabled = !uiState.isLoading
    )

    Spacer(modifier = Modifier.height(16.dp))

    FormField(
        label = "Contraseña",
        value = uiState.password,
        onValueChange = onPasswordChanged,
        placeholder = "••••••••••••••",
        leadingIcon = {
            Icon(
                Lucide.Lock,
                contentDescription = "Password",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    if (passwordVisible) Lucide.EyeOff else Lucide.Eye,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardType = KeyboardType.Password
    )

    Spacer(modifier = Modifier.height(32.dp))

    PrimaryButton(
        text = "Crear cuenta",
        onClick = onRegisterClick,
        isLoading = uiState.isLoading,
        enabled = true
    )
}