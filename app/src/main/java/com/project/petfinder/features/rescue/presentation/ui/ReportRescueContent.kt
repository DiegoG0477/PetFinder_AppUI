package com.project.petfinder.features.rescue.presentation.ui

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
import com.project.petfinder.features.rescue.presentation.ui.component.RescueForm
import com.project.petfinder.features.rescue.presentation.ui.component.RescueHeader
import com.project.petfinder.features.rescue.presentation.viewmodel.ReportRescueUiState
import org.threeten.bp.LocalDate

@Composable
fun ReportRescueContent(
    uiState: ReportRescueUiState,
    onNavigateBack: () -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    onMunicipalitySelected: (Municipality) -> Unit,
    onAdditionalInfoChanged: (String) -> Unit,
    onImageSelected: (Uri) -> Unit,
    onSubmitRescue: () -> Unit
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

            RescueHeader()

            RescueForm(
                uiState = uiState,
                onDateChanged = onDateChanged,
                onMunicipalitySelected = onMunicipalitySelected,
                onAdditionalInfoChanged = onAdditionalInfoChanged,
                onImageSelected = onImageSelected,
                onSubmit = onSubmitRescue
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}