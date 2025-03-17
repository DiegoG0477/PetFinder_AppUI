package com.project.petfinder.features.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.petfinder.features.home.presentation.viewmodel.HomeNavigationEvent
import com.project.petfinder.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToCreateBulletin: () -> Unit,
    onNavigateToReportSighting: () -> Unit,
    onNavigateToReportRescue: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val fabState by viewModel.fabState.collectAsState()

    LaunchedEffect(true) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                HomeNavigationEvent.NavigateToCreateBulletin -> onNavigateToCreateBulletin()
                HomeNavigationEvent.NavigateToReportSighting -> onNavigateToReportSighting()
                HomeNavigationEvent.NavigateToReportRescue -> onNavigateToReportRescue()
                HomeNavigationEvent.NavigateToLogin -> onNavigateToLogin()
                HomeNavigationEvent.Logout -> onLogout()
            }
        }
    }

    HomeContent(
        uiState = uiState,
        fabState = fabState,
        onFabExpandedChange = viewModel::onFabExpandedChange,
        onCreateBulletinClick = viewModel::onCreateBulletinClick,
        onReportSightingClick = viewModel::onReportSightingClick,
        onReportRescueClick = viewModel::onReportRescueClick,
        onReportRescue = viewModel::reportRescue,
        onReportSighting = viewModel::reportSighting,
        onAccountClick = viewModel::onAccountClick
    )
}