package com.project.petfinder.features.rescue.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.features.rescue.presentation.viewmodel.ReportRescueUiState
import org.threeten.bp.LocalDate

@Preview(showBackground = true)
@Composable
fun ReportRescueScreenEmptyPreview() {
    ReportRescueContent(
        uiState = ReportRescueUiState(),
        onNavigateBack = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmitRescue = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ReportRescueScreenLoadingPreview() {
    ReportRescueContent(
        uiState = ReportRescueUiState(isLoading = true),
        onNavigateBack = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmitRescue = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ReportRescueScreenErrorPreview() {
    ReportRescueContent(
        uiState = ReportRescueUiState(
            errorMessage = "Ha ocurrido un error al reportar el rescate"
        ),
        onNavigateBack = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmitRescue = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ReportRescueScreenFilledPreview() {
    val sampleMunicipality = Municipality(
        id = 1,
        name = "Guadalajara"
    )

    ReportRescueContent(
        uiState = ReportRescueUiState(
            date = LocalDate.now(),
            selectedMunicipality = sampleMunicipality,
            additionalInfo = "La mascota se encuentra en buen estado",
            selectedImageUri = "https://example.com/image.jpg".toUri(),
            municipalities = listOf(sampleMunicipality)
        ),
        onNavigateBack = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmitRescue = {}
    )
}