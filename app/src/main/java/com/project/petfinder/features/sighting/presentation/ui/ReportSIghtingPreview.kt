package com.project.petfinder.features.sighting.presentation.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.features.sighting.presentation.viewmodel.ReportSightingUiState
import org.threeten.bp.LocalDate
import androidx.core.net.toUri

@Preview(showBackground = true)
@Composable
fun ReportSightingScreenEmptyPreview() {
    ReportSightingContent(
        uiState = ReportSightingUiState(),
        onNavigateBack = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmitSighting = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ReportSightingScreenLoadingPreview() {
    ReportSightingContent(
        uiState = ReportSightingUiState(isLoading = true),
        onNavigateBack = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmitSighting = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ReportSightingScreenErrorPreview() {
    ReportSightingContent(
        uiState = ReportSightingUiState(
            errorMessage = "Ha ocurrido un error al reportar el avistamiento"
        ),
        onNavigateBack = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmitSighting = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ReportSightingScreenFilledPreview() {
    val sampleMunicipality = Municipality(
        id = 1,
        name = "Guadalajara"
    )

    ReportSightingContent(
        uiState = ReportSightingUiState(
            date = LocalDate.now(),
            selectedMunicipality = sampleMunicipality,
            additionalInfo = "Vi a la mascota cerca del parque",
            selectedImageUri = "https://example.com/image.jpg".toUri(),
            municipalities = listOf(sampleMunicipality)
        ),
        onNavigateBack = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmitSighting = {}
    )
}