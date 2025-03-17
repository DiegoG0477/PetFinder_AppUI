package com.project.petfinder.features.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.petfinder.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onAddPetClick: () -> Unit,
    onPetClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeContent(
        uiState = uiState,
        onAddPetClick = onAddPetClick,
        onPetClick = onPetClick,
        onReportRescue = viewModel::reportRescue,
        onReportSighting = viewModel::reportSighting,
        onShareClick = { /* Handle share */ }
    )
}