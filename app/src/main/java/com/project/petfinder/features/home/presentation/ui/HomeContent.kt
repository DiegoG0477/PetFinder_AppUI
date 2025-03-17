package com.project.petfinder.features.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.composables.icons.lucide.LogIn
import com.composables.icons.lucide.LogOut
import com.composables.icons.lucide.Lucide
import com.project.petfinder.core.ui.component.AppTopBar
import com.project.petfinder.core.ui.component.MultiActionFAB
import com.project.petfinder.features.home.presentation.ui.component.EmptyPetsList
import com.project.petfinder.features.home.presentation.ui.component.PetsList
import com.project.petfinder.features.home.presentation.viewmodel.FabState
import com.project.petfinder.features.home.presentation.viewmodel.HomeUiState

@Composable
fun HomeContent(
    uiState: HomeUiState,
    fabState: FabState,
    onFabExpandedChange: (Boolean) -> Unit,
    onCreateBulletinClick: () -> Unit,
    onReportSightingClick: () -> Unit,
    onReportRescueClick: () -> Unit,
    onReportRescue: (String) -> Unit,
    onReportSighting: (String) -> Unit,
    onAccountClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                actions = {
                    IconButton(onClick = onAccountClick) {
                        Icon(
                            if (uiState.isUserLoggedIn) Lucide.LogOut else Lucide.LogIn,
                            contentDescription = if (uiState.isUserLoggedIn) "Cerrar sesión" else "Iniciar sesión",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            MultiActionFAB(
                expanded = fabState.expanded,
                onExpandedChange = onFabExpandedChange,
                onCreateBulletin = onCreateBulletinClick,
                onReportSighting = onReportSightingClick,
                onReportRescue = onReportRescueClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                uiState.pets.isEmpty() -> {
                    EmptyPetsList(modifier = Modifier.align(Alignment.Center))
                }
                else -> {
                    PetsList(
                        pets = uiState.pets,
                        onReportRescue = onReportRescue,
                        onReportSighting = onReportSighting
                    )
                }
            }
        }
    }
}