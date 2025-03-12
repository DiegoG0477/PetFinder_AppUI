package com.project.petfinder.sighting.presentation.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.composables.icons.lucide.*
import com.project.petfinder.R
import com.project.petfinder.core.ui.theme.Montserrat
import com.project.petfinder.bulletin.presentation.ui.component.DatePicker
import com.project.petfinder.sighting.presentation.viewmodel.ReportSightingViewModel
import org.threeten.bp.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportSightingScreen(
    onNavigateBack: () -> Unit,
    onSightingReported: () -> Unit,
    viewModel: ReportSightingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

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
                            Lucide.ArrowLeft,
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
                text = "REPORTAR AVISTAMIENTO",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFBB6835),
                fontFamily = Montserrat
            )

            Text(
                text = "¿Has visto Firulais? Reporta tu avistamiento al dueño",
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = Montserrat,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
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

            // Date Field
            var showDatePicker by remember { mutableStateOf(false) }
            val dateFormatter = remember {
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            }

            com.project.petfinder.bulletin.presentation.ui.FormField(
                label = "Fecha",
                value = uiState.date.format(dateFormatter),
                onValueChange = { },
                placeholder = "DD/MM/YYYY",
                leadingIcon = { Icon(Lucide.Calendar, "Date", tint = Color.Gray) },
                readOnly = true,
                modifier = Modifier.clickable { showDatePicker = true }
            )

            if (showDatePicker) {
                DatePicker(
                    onDismissRequest = { showDatePicker = false },
                    onDateSelected = { selectedDate ->
                        viewModel.onDateChanged(selectedDate)
                        showDatePicker = false
                    },
                    selectedDate = uiState.date
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Municipality Field
            var dropdownExpanded by remember { mutableStateOf(false) }

            Text(
                text = "Municipio",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

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

            // Additional Information Field
            Text(
                text = "Información adicional",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = uiState.additionalInfo,
                onValueChange = viewModel::onAdditionalInfoChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = {
                    Text(
                        "Describe dónde y en qué condiciones viste a la mascota",
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

            // Image Upload
            val imagePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let { viewModel.onImageSelected(it) }
            }

            if (uiState.selectedImageUri != null) {
                AsyncImage(
                    model = uiState.selectedImageUri,
                    contentDescription = "Imagen del avistamiento",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            TextButton(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFBB6835)
                )
            ) {
                Text(
                    text = if (uiState.selectedImageUri == null) "Subir imagen" else "Cambiar imagen",
                    fontSize = 14.sp,
                    fontFamily = Montserrat
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Report Button
            Button(
                onClick = { viewModel.reportSighting() },
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
                        text = "Reportar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Montserrat
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onSightingReported()
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
    modifier: Modifier = Modifier,
    readOnly: Boolean = false
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = leadingIcon?.let { { it() } },
        readOnly = readOnly,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color(0xFFBB6835)
        )
    )
}