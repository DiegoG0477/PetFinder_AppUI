package com.project.petfinder.features.sighting.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.petfinder.features.sighting.presentation.viewmodel.ReportSightingViewModel

@Composable
fun ReportSightingScreen(
    onNavigateBack: () -> Unit,
    onSightingReported: () -> Unit,
    viewModel: ReportSightingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ReportSightingContent(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onDateChanged = viewModel::onDateChanged,
        onMunicipalitySelected = viewModel::onMunicipalitySelected,
        onAdditionalInfoChanged = viewModel::onAdditionalInfoChanged,
        onImageSelected = viewModel::onImageSelected,
        onSubmitSighting = viewModel::reportSighting
    )

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onSightingReported()
        }
    }
}