package com.project.petfinder.sighting.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.sighting.domain.repository.SightingRepository
import com.project.petfinder.core.domain.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class ReportSightingViewModel @Inject constructor(
    private val sightingRepository: SightingRepository,
    private val locationRepository: LocationRepository,
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
                val municipalities = locationRepository.getMunicipalities()
                _uiState.value = _uiState.value.copy(municipalities = municipalities)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error al cargar municipios: ${e.message}"
                )
            }
        }
    }

    fun onDateChanged(date: LocalDate) {
        _uiState.value = _uiState.value.copy(date = date)
    }

    fun onMunicipalitySelected(municipality: Municipality) {
        _uiState.value = _uiState.value.copy(selectedMunicipality = municipality)
    }

    fun onAdditionalInfoChanged(info: String) {
        _uiState.value = _uiState.value.copy(additionalInfo = info)
    }

    fun onImageSelected(uri: Uri) {
        _uiState.value = _uiState.value.copy(selectedImageUri = uri)
    }

    fun reportSighting() {
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                sightingRepository.reportSighting(
                    petId = petId,
                    date = _uiState.value.date,
                    municipalityId = (_uiState.value.selectedMunicipality?.id ?: "").toString(),
                    additionalInfo = _uiState.value.additionalInfo,
                    imageUri = _uiState.value.selectedImageUri
                )

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error al reportar avistamiento"
                )
            }
        }
    }

    private fun validateInputs(): Boolean {
        if (_uiState.value.selectedMunicipality == null) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Selecciona un municipio"
            )
            return false
        }
        if (_uiState.value.selectedImageUri == null) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "La imagen es requerida"
            )
            return false
        }
        return true
    }
}

data class ReportSightingUiState(
    val date: LocalDate = LocalDate.now(),
    val municipalities: List<Municipality> = emptyList(),
    val selectedMunicipality: Municipality? = null,
    val additionalInfo: String = "",
    val selectedImageUri: Uri? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)