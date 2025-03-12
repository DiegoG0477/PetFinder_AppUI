package com.project.petfinder.register.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.petfinder.core.domain.model.Municipality
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.*
import com.project.petfinder.R
import com.project.petfinder.core.ui.theme.Montserrat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll

@Preview(name = "Registro Normal", showBackground = true)
@Composable
fun RegisterScreenNormalPreview() {
    RegisterScreenPreview()
}

@Preview(name = "Registro Loading", showBackground = true)
@Composable
fun RegisterScreenLoadingPreview() {
    RegisterScreenPreview(
        isLoading = true,
        name = "Juan Pérez",
        email = "juan@example.com",
        password = "contraseña123",
        selectedMunicipality = Municipality(1, "Medellín"),
        municipalities = listOf(
            Municipality(1, "Medellín"),
            Municipality(2, "Envigado"),
            Municipality(3, "Sabaneta")
        )
    )
}

@Preview(name = "Registro Error", showBackground = true)
@Composable
fun RegisterScreenErrorPreview() {
    RegisterScreenPreview(
        errorMessage = "El correo ya está registrado",
        name = "Juan Pérez",
        email = "juan@example.com",
        password = "contraseña123",
        municipalities = listOf(
            Municipality(1, "Medellín"),
            Municipality(2, "Envigado"),
            Municipality(3, "Sabaneta")
        )
    )
}

@Composable
fun RegisterScreenPreview(
    name: String = "",
    email: String = "",
    selectedMunicipality: Municipality? = null,
    password: String = "",
    municipalities: List<Municipality> = emptyList(),
    errorMessage: String? = null,
    isLoading: Boolean = false,
    isSuccess: Boolean = false,
    onNameChanged: (String) -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onMunicipalitySelected: (Municipality) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
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

        // Logo
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pet_logo),
                contentDescription = "PetFinder Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(70.dp)
            )
        }

        // App Name
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Pet",
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                fontFamily = Montserrat
            )
            Text(
                text = "Finder",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFBB6835),
                fontFamily = Montserrat
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Register Header
        Text(
            text = "BIENVENIDO",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFBB6835),
            fontFamily = Montserrat,
            modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = "Complete la información para crear un nuevo perfil",
            fontSize = 14.sp,
            color = Color.Gray,
            fontFamily = Montserrat,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 4.dp, bottom = 24.dp)
        )

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

        // Name Field
        FormField(
            label = "Nombre",
            value = name,
            onValueChange = onNameChanged,
            placeholder = "Jhon Doe",
            leadingIcon = { Icon(Lucide.User, "Name", tint = Color.Gray) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        FormField(
            label = "Correo Electrónico",
            value = email,
            onValueChange = onEmailChanged,
            placeholder = "jhon@doe.com",
            leadingIcon = { Icon(Lucide.Mail, "Email", tint = Color.Gray) },
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Municipality Field
        Text(
            text = "Municipio",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        var dropdownExpanded by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = selectedMunicipality?.name ?: "",
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { dropdownExpanded = true },
            placeholder = {
                Text(
                    "Selecciona un municipio",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = {
                Icon(
                    Lucide.MapPin,
                    contentDescription = "Location",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                Icon(
                    Lucide.ChevronDown,
                    contentDescription = "Expand",
                    tint = Color.Gray
                )
            },
            readOnly = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color(0xFFBB6835)
            )
        ) // Cierre del OutlinedTextField aquí

// El DropdownMenu debe estar fuera del OutlinedTextField
        DropdownMenu(
            expanded = dropdownExpanded,
            onDismissRequest = { dropdownExpanded = false },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            municipalities.forEach { municipality ->
                DropdownMenuItem(
                    text = { Text(municipality.name) },
                    onClick = {
                        onMunicipalitySelected(municipality)
                        dropdownExpanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field (debe estar fuera del OutlinedTextField anterior)
        var passwordVisible by remember { mutableStateOf(false) }

        FormField(
            label = "Contraseña",
            value = password,
            onValueChange = onPasswordChanged,
            placeholder = "••••••••••••••",
            leadingIcon = { Icon(Lucide.Lock, "Password", tint = Color.Gray) },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        if (passwordVisible) Lucide.EyeOff else Lucide.Eye,
                        if (passwordVisible) "Hide password" else "Show password",
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Register Button
        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB6835)),
            shape = RoundedCornerShape(8.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = "Crear cuenta",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Montserrat
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login Link
        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Ya tienes una cuenta? ",
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = Montserrat
            )
            TextButton(onClick = onLoginClick) {
                Text(
                    text = "Inicia sesión",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFBB6835),
                    fontFamily = Montserrat
                )
            }
        }
    }
}