package com.project.petfinder.bulletin.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.bulletin.domain.repository.BulletinRepository
import com.project.petfinder.core.domain.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class CreateBulletinViewModel @Inject constructor(
    private val bulletinRepository: BulletinRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateBulletinUiState())
    val uiState: StateFlow<CreateBulletinUiState> = _uiState

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

    fun onPetNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(petName = name)
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

    fun createBulletin() {
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                bulletinRepository.createBulletin(
                    petName = _uiState.value.petName,
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
                    errorMessage = e.message ?: "Error al crear el boletín"
                )
            }
        }
    }

    private fun validateInputs(): Boolean {
        if (_uiState.value.petName.isBlank()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "El nombre de la mascota es requerido"
            )
            return false
        }
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

data class CreateBulletinUiState(
    val petName: String = "",
    val date: LocalDate = LocalDate.now(),
    val municipalities: List<Municipality> = emptyList(),
    val selectedMunicipality: Municipality? = null,
    val additionalInfo: String = "",
    val selectedImageUri: Uri? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)