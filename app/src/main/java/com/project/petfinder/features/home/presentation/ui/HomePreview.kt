package com.project.petfinder.features.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.petfinder.features.home.domain.model.Pet
import com.project.petfinder.features.home.presentation.viewmodel.FabState
import com.project.petfinder.features.home.presentation.viewmodel.HomeUiState
import java.time.LocalDateTime

@Preview(showBackground = true)
@Composable
fun HomeScreenEmptyPreview() {
    HomeContent(
        uiState = HomeUiState(),
        fabState = FabState(),
        onFabExpandedChange = {},
        onCreateBulletinClick = {},
        onReportSightingClick = {},
        onReportRescueClick = {},
        onReportRescue = {},
        onReportSighting = {},
        onAccountClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenLoadingPreview() {
    HomeContent(
        uiState = HomeUiState(isLoading = true),
        fabState = FabState(),
        onFabExpandedChange = {},
        onCreateBulletinClick = {},
        onReportSightingClick = {},
        onReportRescueClick = {},
        onReportRescue = {},
        onReportSighting = {},
        onAccountClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenWithPetsPreview() {
    val samplePets = listOf(
        Pet(
            id = "1",
            name = "Firulais",
            imageUrl = "https://example.com/dog.jpg",
            lastSeenDate = LocalDateTime.now().toString(),
            description = "perrito perdido",
            ownerName = "Julián Pérez",
            lastSeenLocation = "Guadalajara"
        ),
        Pet(
            id = "2",
            name = "Luna",
            imageUrl = "https://example.com/cat.jpg",
            lastSeenDate = LocalDateTime.now().minusDays(2).toString(),
            description = "perrito perdido 2",
            ownerName = "Martín González",
            lastSeenLocation = "Guadalajara"
        )
    )

    HomeContent(
        uiState = HomeUiState(pets = samplePets),
        fabState = FabState(),
        onFabExpandedChange = {},
        onCreateBulletinClick = {},
        onReportSightingClick = {},
        onReportRescueClick = {},
        onReportRescue = {},
        onReportSighting = {},
        onAccountClick = {}
    )
}