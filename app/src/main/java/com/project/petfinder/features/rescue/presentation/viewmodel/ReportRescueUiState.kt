package com.project.petfinder.features.rescue.presentation.viewmodel

import android.net.Uri
import com.project.petfinder.core.domain.model.Municipality
import org.threeten.bp.LocalDate

data class ReportRescueUiState(
    val date: LocalDate = LocalDate.now(),
    val municipalities: List<Municipality> = emptyList(),
    val selectedMunicipality: Municipality? = null,
    val additionalInfo: String = "",
    val selectedImageUri: Uri? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)