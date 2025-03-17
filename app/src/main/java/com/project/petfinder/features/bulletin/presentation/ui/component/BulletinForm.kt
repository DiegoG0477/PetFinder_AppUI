package com.project.petfinder.features.bulletin.presentation.ui.component

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.ui.component.DatePickerField
import com.project.petfinder.core.ui.component.FormField
import com.project.petfinder.core.ui.component.ImageSelector
import com.project.petfinder.core.ui.component.MunicipalityDropdown
import com.project.petfinder.core.ui.component.PrimaryButton
import org.threeten.bp.LocalDate

@Composable
fun BulletinForm(
    petName: String,
    date: LocalDate,
    selectedMunicipality: Municipality?,
    additionalInfo: String,
    selectedImageUri: Uri?,
    onPetNameChanged: (String) -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    onMunicipalitySelected: (Municipality) -> Unit,
    onAdditionalInfoChanged: (String) -> Unit,
    onImageSelected: (Uri) -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        FormField(
            value = petName,
            onValueChange = onPetNameChanged,
            label = "Nombre de la mascota",
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Date Picker
        DatePickerField(
            date = date,
            onDateChanged = onDateChanged,
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Municipality Selector
        MunicipalityDropdown(
            selectedMunicipality = selectedMunicipality,
            onMunicipalitySelected = onMunicipalitySelected,
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        FormField(
            value = additionalInfo,
            onValueChange = onAdditionalInfoChanged,
            label = "Información adicional",
            minLines = 3,
            maxLines = 5,
            singleLine = false,
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        ImageSelector(
            selectedImageUri = selectedImageUri,
            onImageSelected = onImageSelected,
            enabled = !isLoading,
            contentDescription = "Imagen del boletín"
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Crear Boletín",
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            isLoading = isLoading
        )
    }
}