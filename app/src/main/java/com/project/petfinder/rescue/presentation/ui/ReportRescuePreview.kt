package com.project.petfinder.rescue.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.*
import com.project.petfinder.R
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.register.presentation.ui.FormField
import com.project.petfinder.core.ui.theme.Montserrat
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@Preview(name = "Formulario Vacío", showBackground = true)
@Composable
fun ReportRescueEmptyPreview() {
    FakeReportRescueScreen()
}

@Preview(name = "Formulario Completo", showBackground = true)
@Composable
fun ReportRescueFilledPreview() {
    FakeReportRescueScreen(
        date = LocalDate.now(),
        selectedMunicipality = Municipality(1, "Medellín"),
        additionalInfo = "La mascota está en buen estado, la encontré en el parque central",
        selectedImageUri = R.drawable.pet_logo,
        municipalities = listOf(
            Municipality(1, "Medellín"),
            Municipality(2, "Envigado"),
            Municipality(3, "Sabaneta")
        )
    )
}

@Preview(name = "Cargando", showBackground = true)
@Composable
fun ReportRescueLoadingPreview() {
    FakeReportRescueScreen(
        isLoading = true,
        additionalInfo = "Mascota rescatada en condición estable..."
    )
}

@Preview(name = "Error", showBackground = true)
@Composable
fun ReportRescueErrorPreview() {
    FakeReportRescueScreen(
        errorMessage = "Debes seleccionar un municipio",
        selectedImageUri = R.drawable.pet_logo
    )
}

@Preview(name = "Con Imagen", showBackground = true)
@Composable
fun ReportRescueWithImagePreview() {
    FakeReportRescueScreen(
        selectedImageUri = R.drawable.pet_logo,
        additionalInfo = "Mascota encontrada cerca del centro comercial"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FakeReportRescueScreen(
    date: LocalDate = LocalDate.now(),
    selectedMunicipality: Municipality? = null,
    additionalInfo: String = "",
    selectedImageUri: Any? = null,
    municipalities: List<Municipality> = emptyList(),
    isLoading: Boolean = false,
    isSuccess: Boolean = false,
    errorMessage: String? = null,
    onNavigateBack: () -> Unit = {},
    onRescueReported: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    var dropdownExpanded by remember { mutableStateOf(false) }
    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.pet_logo_white),
                        contentDescription = "PetFinder Logo",
                        modifier = Modifier.size(32.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Lucide.ArrowLeft,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFBB6835)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Header
            Text(
                text = "NOTIFICA TU RESCATE",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFBB6835),
                fontFamily = Montserrat
            )

            Text(
                text = "¿Tienes a Firulais? Avísale al dueño que está seguro",
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = Montserrat,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
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

            // Date Field
            FormField(
                label = "Fecha",
                value = date.format(dateFormatter),
                onValueChange = { },
                placeholder = "DD/MM/YYYY",
                leadingIcon = { Icon(Lucide.Calendar, "Date", tint = Color.Gray) },
                readOnly = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Municipality Field
            Text(
                text = "Municipio",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = selectedMunicipality?.name ?: "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { dropdownExpanded = true },
                placeholder = {
                    Text(
                        text = "Selecciona un municipio",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Lucide.MapPin,
                        contentDescription = "Location",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Lucide.ChevronDown,
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
                municipalities.forEach { municipality ->
                    DropdownMenuItem(
                        text = { Text(municipality.name) },
                        onClick = { dropdownExpanded = false }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Additional Information Field
            OutlinedTextField(
                value = additionalInfo,
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                label = { Text("Información adicional") },
                placeholder = {
                    Text(
                        text = "Describe el estado de la mascota y dónde se encuentra",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFFBB6835)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Image Section
            if (selectedImageUri != null) {
                Image(
                    painter = painterResource(id = R.drawable.pet_logo),
                    contentDescription = "Imagen del rescate",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            TextButton(
                onClick = { },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFBB6835)
                )
            ) {
                Text(
                    text = if (selectedImageUri == null) "Subir imagen" else "Cambiar imagen",
                    fontSize = 14.sp,
                    fontFamily = Montserrat
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Notify Button
            Button(
                onClick = { },
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
                        text = "Notificar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Montserrat
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            onRescueReported()
        }
    }
}