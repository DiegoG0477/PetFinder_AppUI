package com.project.petfinder.features.bulletin.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.features.bulletin.presentation.viewmodel.CreateBulletinUiState
import org.threeten.bp.LocalDate

@Preview(showBackground = true)
@Composable
fun CreateBulletinScreenEmptyPreview() {
    CreateBulletinContent(
        uiState = CreateBulletinUiState(),
        onNavigateBack = {},
        onPetNameChanged = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmit = {}
    )
}

@Preview(showBackground = true)
@Composable
fun CreateBulletinScreenLoadingPreview() {
    CreateBulletinContent(
        uiState = CreateBulletinUiState(isLoading = true),
        onNavigateBack = {},
        onPetNameChanged = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmit = {}
    )
}

@Preview(showBackground = true)
@Composable
fun CreateBulletinScreenErrorPreview() {
    CreateBulletinContent(
        uiState = CreateBulletinUiState(
            errorMessage = "Ha ocurrido un error al crear el boletín"
        ),
        onNavigateBack = {},
        onPetNameChanged = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmit = {}
    )
}

@Preview(showBackground = true)
@Composable
fun CreateBulletinScreenFilledPreview() {
    val sampleMunicipality = Municipality(
        id = 1,
        name = "Guadalajara"
    )

    CreateBulletinContent(
        uiState = CreateBulletinUiState(
            petName = "Firulais",
            date = LocalDate.of(2025, 3, 17),
            selectedMunicipality = sampleMunicipality,
            additionalInfo = "Se perdió cerca del parque",
            selectedImageUri = "fake://uri".toUri(),
            municipalities = listOf(sampleMunicipality)
        ),
        onNavigateBack = {},
        onPetNameChanged = {},
        onDateChanged = {},
        onMunicipalitySelected = {},
        onAdditionalInfoChanged = {},
        onImageSelected = {},
        onSubmit = {}
    )
}