package com.project.petfinder.features.rescue.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.petfinder.features.rescue.presentation.viewmodel.ReportRescueViewModel

@Composable
fun ReportRescueScreen(
    onNavigateBack: () -> Unit,
    onRescueReported: () -> Unit,
    viewModel: ReportRescueViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ReportRescueContent(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onDateChanged = viewModel::onDateChanged,
        onMunicipalitySelected = viewModel::onMunicipalitySelected,
        onAdditionalInfoChanged = viewModel::onAdditionalInfoChanged,
        onImageSelected = viewModel::onImageSelected,
        onSubmitRescue = viewModel::reportRescue
    )

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onRescueReported()
        }
    }
}