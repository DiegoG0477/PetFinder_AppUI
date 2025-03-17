package com.project.petfinder.features.rescue.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.core.domain.usecase.GetMunicipalitiesUseCase
import com.project.petfinder.features.rescue.domain.usecase.ReportRescueUseCase
import com.project.petfinder.features.rescue.domain.usecase.ValidateRescueInputsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class ReportRescueViewModel @Inject constructor(
    private val reportRescueUseCase: ReportRescueUseCase,
    private val validateRescueInputsUseCase: ValidateRescueInputsUseCase,
    private val getMunicipalitiesUseCase: GetMunicipalitiesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val petId: String = checkNotNull(savedStateHandle["petId"])
    private val _uiState = MutableStateFlow(ReportRescueUiState())
    val uiState: StateFlow<ReportRescueUiState> = _uiState

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

    fun reportRescue() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                // Primero validamos los inputs
                when (val validationResult = validateRescueInputsUseCase(
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
                        processRescueReport()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error al reportar rescate"
                )
            }
        }
    }

    private suspend fun processRescueReport() {
        try {
            val result = reportRescueUseCase(
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
                        errorMessage = exception.message ?: "Error al reportar rescate"
                    )
                }
            )
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = e.message ?: "Error al reportar rescate"
            )
        }
    }
}