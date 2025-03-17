package com.project.petfinder.features.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.composables.icons.lucide.Share2
import com.project.petfinder.core.ui.component.AppTopBar
import com.project.petfinder.features.home.presentation.ui.component.EmptyPetsList
import com.project.petfinder.features.home.presentation.ui.component.PetsList
import com.project.petfinder.features.home.presentation.viewmodel.HomeUiState

@Composable
fun HomeContent(
    uiState: HomeUiState,
    onAddPetClick: () -> Unit,
    onPetClick: (String) -> Unit,
    onReportRescue: (String) -> Unit,
    onReportSighting: (String) -> Unit,
    onShareClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                actions = {
                    IconButton(onClick = onShareClick) {
                        Icon(
                            Lucide.Share2,
                            contentDescription = "Compartir",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPetClick,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Lucide.Plus, contentDescription = "Agregar mascota")
            }
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
                        onPetClick = onPetClick,
                        onReportRescue = onReportRescue,
                        onReportSighting = onReportSighting
                    )
                }
            }
        }
    }
}