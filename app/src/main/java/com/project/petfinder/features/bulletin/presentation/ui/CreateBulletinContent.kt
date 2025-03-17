package com.project.petfinder.features.bulletin.presentation.ui

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.ui.component.AppTopBar
import com.project.petfinder.core.ui.theme.Montserrat
import com.project.petfinder.features.bulletin.presentation.ui.component.BulletinForm
import com.project.petfinder.features.bulletin.presentation.viewmodel.CreateBulletinUiState
import org.threeten.bp.LocalDate

@Composable
fun CreateBulletinContent(
    uiState: CreateBulletinUiState,
    onNavigateBack: () -> Unit,
    onPetNameChanged: (String) -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    onMunicipalitySelected: (Municipality) -> Unit,
    onAdditionalInfoChanged: (String) -> Unit,
    onImageSelected: (Uri) -> Unit,
    onSubmit: () -> Unit
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

            Text(
                text = "CREAR BOLETÍN DE BÚSQUEDA",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = Montserrat
            )

            Text(
                text = "Crea un nuevo boletín de búsqueda para localizar a tu mascota",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = Montserrat,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            // Bulletin Form
            BulletinForm(
                petName = uiState.petName,
                date = uiState.date,
                selectedMunicipality = uiState.selectedMunicipality,
                additionalInfo = uiState.additionalInfo,
                selectedImageUri = uiState.selectedImageUri,
                onPetNameChanged = onPetNameChanged,
                onDateChanged = onDateChanged,
                onMunicipalitySelected = onMunicipalitySelected,
                onAdditionalInfoChanged = onAdditionalInfoChanged,
                onImageSelected = onImageSelected,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                onSubmit = onSubmit
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}