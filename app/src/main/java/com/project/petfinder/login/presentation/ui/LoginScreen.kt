package com.project.petfinder.login.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.composables.icons.lucide.*
import com.project.petfinder.R
import com.project.petfinder.login.presentation.viewmodel.LoginViewModel

@Composable
fun FakeLoginScreen(
    email: String = "",
    password: String = "",
    errorMessage: String? = null,
    isLoading: Boolean = false,
    isSuccess: Boolean = false,
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onLogin: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ... (Todo el contenido UI igual que en LoginScreen original)
        // Reemplazar todas las referencias a uiState con los parámetros recibidos
        // Ejemplo: uiState.email -> email

        // Modificar el LaunchedEffect para usar el parámetro isSuccess
        LaunchedEffect(isSuccess) {
            if (isSuccess) {
                onLoginSuccess()
            }
        }
    }
}

@Preview(name = "Login Normal", showBackground = true)
@Composable
fun PreviewLoginScreen() {
    FakeLoginScreen(
        email = "test@example.com",
        password = "password123",
        errorMessage = null,
        isLoading = false
    )
}

@Preview(name = "Login Loading", showBackground = true)
@Composable
fun PreviewLoginScreenLoading() {
    FakeLoginScreen(
        email = "test@example.com",
        password = "password123",
        errorMessage = "Error de ejemplo",
        isLoading = true
    )
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        Box(
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pet_logo),
                contentDescription = "PetFinder Logo",
                contentScale = ContentScale.Fit
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
                fontFamily = FontFamily.SansSerif
            )

            Text(
                text = "Finder",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFCC5500),
                fontFamily = FontFamily.SansSerif
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Login Header
        Text(
            text = "INGRESAR",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFCC5500),
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = "Por favor inicie sesión para continuar",
            fontSize = 14.sp,
            color = Color.Gray,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 4.dp, bottom = 24.dp)
        )

        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage,  // Removed !! operator since we already check for null
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,  // Using Material3 typography
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        // Email Field Label
        Text(
            text = "Correo Electrónico",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        // Email TextField
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { email: String -> viewModel.onEmailChanged(email) },  // Fixed 'it' parameter
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "jhon@doe.com",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Lucide.Mail,
                    contentDescription = "Email Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color(0xFFCC5500)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field Label
        Text(
            text = "Contraseña",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        var passwordVisible by remember { mutableStateOf(false) }

        // Password TextField
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { password: String -> viewModel.onPasswordChanged(password) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "••••••••••••••",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Lucide.Lock,  // Added parentheses for proper initialization
                    contentDescription = "Password Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Lucide.EyeOff else Lucide.Eye,  // Added parentheses
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        tint = Color.Gray
                    )
                }
            },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color(0xFFCC5500)
            )
        )


        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = { viewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC5500)),
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
                    text = "Ingresar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Register Link
        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿No tienes una cuenta? ",
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = FontFamily.SansSerif
            )
            TextButton(onClick = onRegisterClick) {
                Text(
                    text = "Regístrate",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFCC5500),
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }

    // Handle login success
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onLoginSuccess()
        }
    }
}