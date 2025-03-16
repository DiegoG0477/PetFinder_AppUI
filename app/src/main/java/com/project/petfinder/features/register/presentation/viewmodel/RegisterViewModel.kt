package com.project.petfinder.features.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.features.register.domain.repository.RegisterRepository
import com.project.petfinder.core.domain.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

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

    fun onNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onMunicipalitySelected(municipality: Municipality) {
        _uiState.value = _uiState.value.copy(selectedMunicipality = municipality)
    }

    fun register() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            if (!validateInputs()) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                return@launch
            }

            try {
                val result = registerRepository.register(
                    name = _uiState.value.name,
                    email = _uiState.value.email,
                    password = _uiState.value.password,
                    municipalityId = (_uiState.value.selectedMunicipality?.id ?: 0).toString()
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = result.isSuccess,
                    errorMessage = if (!result.isSuccess) result.errorMessage else null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error desconocido al registrar"
                )
            }
        }
    }

    private fun validateInputs(): Boolean {
        if (_uiState.value.name.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "El nombre es requerido")
            return false
        }
        if (_uiState.value.email.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "El correo electrónico es requerido")
            return false
        }
        if (!_uiState.value.email.contains("@")) {
            _uiState.value = _uiState.value.copy(errorMessage = "El correo electrónico no es válido")
            return false
        }
        if (_uiState.value.selectedMunicipality == null) {
            _uiState.value = _uiState.value.copy(errorMessage = "Selecciona un municipio")
            return false
        }
        if (_uiState.value.password.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "La contraseña es requerida")
            return false
        }
        if (_uiState.value.password.length < 6) {
            _uiState.value = _uiState.value.copy(errorMessage = "La contraseña debe tener al menos 6 caracteres")
            return false
        }
        return true
    }
}

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val municipalities: List<Municipality> = emptyList(),
    val selectedMunicipality: Municipality? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

data class Municipality(
    val id: String,
    val name: String
)