package com.project.petfinder.register.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.composables.icons.lucide.*
import com.project.petfinder.R
import com.project.petfinder.register.presentation.viewmodel.RegisterViewModel
import com.project.petfinder.ui.theme.Montserrat

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
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

        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage!!,
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
            value = uiState.name,
            onValueChange = viewModel::onNameChanged,
            placeholder = "Jhon Doe",
            leadingIcon = { Icon(Lucide.User, "Name", tint = Color.Gray) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        FormField(
            label = "Correo Electrónico",
            value = uiState.email,
            onValueChange = viewModel::onEmailChanged,
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
            value = uiState.selectedMunicipality?.name ?: "",
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
        )

        DropdownMenu(
            expanded = dropdownExpanded,
            onDismissRequest = { dropdownExpanded = false },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            uiState.municipalities.forEach { municipality ->
                DropdownMenuItem(
                    text = { Text(municipality.name) },
                    onClick = {
                        viewModel.onMunicipalitySelected(municipality)
                        dropdownExpanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        var passwordVisible by remember { mutableStateOf(false) }

        FormField(
            label = "Contraseña",
            value = uiState.password,
            onValueChange = viewModel::onPasswordChanged,
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
            onClick = { viewModel.register() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB6835)),
            shape = RoundedCornerShape(8.dp),
            enabled = !uiState.isLoading
        ) {
            if (uiState.isLoading) {
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

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onRegisterSuccess()
        }
    }
}

@Composable
fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = leadingIcon?.let { { it() } },
        trailingIcon = trailingIcon?.let { { it() } },
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color(0xFFBB6835)
        )
    )
}