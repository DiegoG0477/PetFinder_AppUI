package com.project.petfinder.features.login.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.petfinder.core.ui.theme.Montserrat

@Composable
fun LoginHeader() {
    Text(
        text = "INGRESAR",
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = Montserrat
    )

    Text(
        text = "Por favor inicie sesión para continuar",
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontFamily = Montserrat,
        modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
    )
}