package com.project.petfinder.features.rescue.presentation.ui.component

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Lucide
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.ui.component.DatePicker
import com.project.petfinder.core.ui.component.FormField
import com.project.petfinder.core.ui.component.ImageSelector
import com.project.petfinder.core.ui.component.MunicipalityDropdown
import com.project.petfinder.core.ui.component.PrimaryButton
import com.project.petfinder.features.rescue.presentation.viewmodel.ReportRescueUiState
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun RescueForm(
    uiState: ReportRescueUiState,
    onDateChanged: (LocalDate) -> Unit,
    onMunicipalitySelected: (Municipality) -> Unit,
    onAdditionalInfoChanged: (String) -> Unit,
    onImageSelected: (Uri) -> Unit,
    onSubmit: () -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

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
        label = "Fecha",
        value = uiState.date.format(dateFormatter),
        onValueChange = { },
        placeholder = "DD/MM/YYYY",
        leadingIcon = {
            Icon(
                Lucide.Calendar,
                contentDescription = "Date",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        readOnly = true,
        modifier = Modifier.clickable { showDatePicker = true }
    )

    if (showDatePicker) {
        DatePicker(
            onDismissRequest = { showDatePicker = false },
            onDateSelected = { selectedDate ->
                onDateChanged(selectedDate)
                showDatePicker = false
            },
            selectedDate = uiState.date
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Municipio",
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium
        ),
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    MunicipalityDropdown(
        selectedMunicipality = uiState.selectedMunicipality,
        municipalities = uiState.municipalities,
        onMunicipalitySelected = onMunicipalitySelected,
        enabled = !uiState.isLoading
    )

    Spacer(modifier = Modifier.height(16.dp))

    FormField(
        label = "Información adicional",
        value = uiState.additionalInfo,
        onValueChange = onAdditionalInfoChanged,
        placeholder = "Describe el estado de la mascota y dónde se encuentra",
        modifier = Modifier.height(120.dp)
    )

    Spacer(modifier = Modifier.height(24.dp))

    ImageSelector(
        selectedImageUri = uiState.selectedImageUri,
        onImageSelected = onImageSelected,
        enabled = !uiState.isLoading,
        contentDescription = "Imagen del rescate"
    )

    Spacer(modifier = Modifier.height(32.dp))

    PrimaryButton(
        text = "Notificar",
        onClick = onSubmit,
        isLoading = uiState.isLoading,
        enabled = true
    )
}