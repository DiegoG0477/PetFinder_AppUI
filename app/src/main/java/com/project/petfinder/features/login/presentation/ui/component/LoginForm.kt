package com.project.petfinder.features.login.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Eye
import com.composables.icons.lucide.EyeOff
import com.composables.icons.lucide.Lock
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.project.petfinder.core.ui.component.FormField
import com.project.petfinder.core.ui.component.PrimaryButton

@Composable
fun LoginForm(
    email: String,
    password: String,
    isLoading: Boolean,
    errorMessage: String?,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }

    FormField(
        value = email,
        onValueChange = onEmailChanged,
        label = "Correo Electrónico",
        placeholder = "jhon@doe.com",
        leadingIcon = {
            Icon(
                imageVector = Lucide.Mail,
                contentDescription = "Email Icon",
                modifier = Modifier.size(20.dp)
            )
        },
        keyboardType = KeyboardType.Email,
        isError = errorMessage != null,
        errorMessage = errorMessage
    )

    Spacer(modifier = Modifier.height(16.dp))

    FormField(
        value = password,
        onValueChange = onPasswordChanged,
        label = "Contraseña",
        placeholder = "••••••••••••••",
        leadingIcon = {
            Icon(
                imageVector = Lucide.Lock,
                contentDescription = "Password Icon",
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Lucide.EyeOff else Lucide.Eye,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardType = KeyboardType.Password
    )

    Spacer(modifier = Modifier.height(32.dp))

    PrimaryButton(
        text = "Ingresar",
        onClick = onLoginClick,
        isLoading = isLoading,
        enabled = true
    )
}