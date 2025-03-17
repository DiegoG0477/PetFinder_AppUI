package com.project.petfinder.features.sighting.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.core.domain.usecase.GetMunicipalitiesUseCase
import com.project.petfinder.features.sighting.domain.usecase.ReportSightingUseCase
import com.project.petfinder.features.sighting.domain.usecase.ValidateSightingInputsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class ReportSightingViewModel @Inject constructor(
    private val reportSightingUseCase: ReportSightingUseCase,
    private val validateSightingInputsUseCase: ValidateSightingInputsUseCase,
    private val getMunicipalitiesUseCase: GetMunicipalitiesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val petId: String = checkNotNull(savedStateHandle["petId"])
    private val _uiState = MutableStateFlow(ReportSightingUiState())
    val uiState: StateFlow<ReportSightingUiState> = _uiState

    init {
        loadMunicipalities()
    }

    private fun loadMunicipalities() {
        viewModelScope.launch {
            try {
                val municipalities = getMunicipalitiesUseCase()
                _uiState.value = _uiState.value.copy(municipalities = municipalities)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error al cargar municipios: ${e.message}"
                )
            }
        }
    }

    fun onDateChanged(date: LocalDate) {
        _uiState.value = _uiState.value.copy(
            date = date,
            errorMessage = null
        )
    }

    fun onMunicipalitySelected(municipality: Municipality) {
        _uiState.value = _uiState.value.copy(
            selectedMunicipality = municipality,
            errorMessage = null
        )
    }

    fun onAdditionalInfoChanged(info: String) {
        _uiState.value = _uiState.value.copy(
            additionalInfo = info,
            errorMessage = null
        )
    }

    fun onImageSelected(uri: Uri) {
        _uiState.value = _uiState.value.copy(
            selectedImageUri = uri,
            errorMessage = null
        )
    }

    fun reportSighting() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                // Primero validamos los inputs
                when (val validationResult = validateSightingInputsUseCase(
                    date = _uiState.value.date,
                    selectedMunicipality = _uiState.value.selectedMunicipality,
                    imageUri = _uiState.value.selectedImageUri,
                    additionalInfo = _uiState.value.additionalInfo
                )) {
                    is OperationResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = validationResult.message
                        )
                        return@launch
                    }
                    is OperationResult.Success -> {
                        processSightingReport()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error al reportar avistamiento"
                )
            }
        }
    }

    private suspend fun processSightingReport() {
        try {
            val result = reportSightingUseCase(
                petId = petId,
                date = _uiState.value.date,
                municipalityId = (_uiState.value.selectedMunicipality?.id ?: "").toString(),
                additionalInfo = _uiState.value.additionalInfo,
                imageUri = _uiState.value.selectedImageUri
            )

            result.fold(
                onSuccess = { operationResult ->
                    when (operationResult) {
                        is OperationResult.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                isSuccess = true
                            )
                        }
                        is OperationResult.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                errorMessage = operationResult.message
                            )
                        }
                    }
                },
                onFailure = { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Error al reportar avistamiento"
                    )
                }
            )
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = e.message ?: "Error al reportar avistamiento"
            )
        }
    }
}