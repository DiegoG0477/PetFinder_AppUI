package com.project.petfinder.features.bulletin.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.petfinder.features.bulletin.presentation.viewmodel.CreateBulletinViewModel

@Composable
fun CreateBulletinScreen(
    onNavigateBack: () -> Unit,
    onBulletinCreated: () -> Unit,
    viewModel: CreateBulletinViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    CreateBulletinContent(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onPetNameChanged = viewModel::onPetNameChanged,
        onDateChanged = viewModel::onDateChanged,
        onMunicipalitySelected = viewModel::onMunicipalitySelected,
        onAdditionalInfoChanged = viewModel::onAdditionalInfoChanged,
        onImageSelected = viewModel::onImageSelected,
        onSubmit = viewModel::createBulletin
    )

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onBulletinCreated()
        }
    }
}