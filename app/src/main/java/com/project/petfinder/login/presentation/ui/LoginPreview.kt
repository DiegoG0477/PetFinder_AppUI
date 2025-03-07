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
import com.composables.icons.lucide.*
import com.project.petfinder.R
import com.project.petfinder.ui.theme.Montserrat

@Composable
fun LoginScreenPreview(
    email: String = "",
    password: String = "",
    errorMessage: String? = null,
    isLoading: Boolean = false,
    isSuccess: Boolean = false,
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onLoginClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }

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

        Spacer(modifier = Modifier.height(60.dp))

        // Login Header
        Text(
            text = "INGRESAR",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFBB6835),
            fontFamily = Montserrat,
            modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = "Por favor inicie sesión para continuar",
            fontSize = 14.sp,
            color = Color.Gray,
            fontFamily = Montserrat,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 4.dp, bottom = 24.dp)
        )

        // Error Message
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

        // Email Field
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

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChanged,
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
                focusedBorderColor = Color(0xFFBB6835)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
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

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "••••••••••••••",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Lucide.Lock,
                    contentDescription = "Password Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Lucide.EyeOff else Lucide.Eye,
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
                focusedBorderColor = Color(0xFFBB6835)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = onLoginClick,
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
                    text = "Ingresar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Montserrat
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
                fontFamily = Montserrat
            )
            TextButton(onClick = onRegisterClick) {
                Text(
                    text = "Regístrate",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFBB6835),
                    fontFamily = Montserrat
                )
            }
        }
    }
}

@Preview(name = "Login Normal", showBackground = true)
@Composable
fun LoginScreenNormalPreview() {
    LoginScreenPreview(
        email = "usuario@ejemplo.com",
        password = "contraseña123"
    )
}

@Preview(name = "Login Loading", showBackground = true)
@Composable
fun LoginScreenLoadingPreview() {
    LoginScreenPreview(
        email = "usuario@ejemplo.com",
        password = "contraseña123",
        isLoading = true
    )
}

@Preview(name = "Login Error", showBackground = true)
@Composable
fun LoginScreenErrorPreview() {
    LoginScreenPreview(
        email = "usuario@ejemplo.com",
        password = "contraseña123",
        errorMessage = "Credenciales inválidas. Por favor intente nuevamente."
    )
}