package com.project.petfinder.features.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.core.domain.usecase.GetMunicipalitiesUseCase
import com.project.petfinder.features.register.domain.usecase.RegisterUserUseCase
import com.project.petfinder.features.register.domain.usecase.ValidateRegisterInputsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validateRegisterInputsUseCase: ValidateRegisterInputsUseCase,
    private val getMunicipalitiesUseCase: GetMunicipalitiesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

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

    fun onNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(
            name = name,
            errorMessage = null
        )
    }

    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            errorMessage = null
        )
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            errorMessage = null
        )
    }

    fun onMunicipalitySelected(municipality: Municipality) {
        _uiState.value = _uiState.value.copy(
            selectedMunicipality = municipality,
            errorMessage = null
        )
    }

    fun register() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                // Primero validamos los inputs
                when (val validationResult = validateRegisterInputsUseCase(
                    name = _uiState.value.name,
                    email = _uiState.value.email,
                    password = _uiState.value.password,
                    selectedMunicipality = _uiState.value.selectedMunicipality
                )) {
                    is OperationResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = validationResult.message
                        )
                        return@launch
                    }
                    is OperationResult.Success -> {
                        // Procedemos con el registro si la validación es exitosa
                        processRegistration()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error desconocido al registrar"
                )
            }
        }
    }

    private suspend fun processRegistration() {
        try {
            val result = registerUserUseCase(
                name = _uiState.value.name,
                email = _uiState.value.email,
                password = _uiState.value.password,
                municipalityId = (_uiState.value.selectedMunicipality?.id ?: "").toString()
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