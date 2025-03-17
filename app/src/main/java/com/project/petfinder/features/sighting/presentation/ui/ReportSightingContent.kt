package com.project.petfinder.features.sighting.presentation.ui

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.ui.component.AppTopBar
import com.project.petfinder.features.sighting.presentation.ui.component.SightingForm
import com.project.petfinder.features.sighting.presentation.ui.component.SightingHeader
import com.project.petfinder.features.sighting.presentation.viewmodel.ReportSightingUiState
import org.threeten.bp.LocalDate

@Composable
fun ReportSightingContent(
    uiState: ReportSightingUiState,
    onNavigateBack: () -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    onMunicipalitySelected: (Municipality) -> Unit,
    onAdditionalInfoChanged: (String) -> Unit,
    onImageSelected: (Uri) -> Unit,
    onSubmitSighting: () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            AppTopBar(
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            SightingHeader()

            SightingForm(
                uiState = uiState,
                onDateChanged = onDateChanged,
                onMunicipalitySelected = onMunicipalitySelected,
                onAdditionalInfoChanged = onAdditionalInfoChanged,
                onImageSelected = onImageSelected,
                onSubmit = onSubmitSighting
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}