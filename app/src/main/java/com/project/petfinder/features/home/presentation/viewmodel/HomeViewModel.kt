package com.project.petfinder.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.home.domain.model.Pet
import com.project.petfinder.features.home.domain.usecase.GetLostPetsUseCase
import com.project.petfinder.features.home.domain.usecase.ReportPetRescueUseCase
import com.project.petfinder.features.home.domain.usecase.ReportPetSightingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLostPetsUseCase: GetLostPetsUseCase,
    private val reportPetRescueUseCase: ReportPetRescueUseCase,
    private val reportPetSightingUseCase: ReportPetSightingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadPets()
    }

    private fun loadPets() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val result = getLostPetsUseCase()
                result.fold(
                    onSuccess = { pets ->
                        _uiState.update {
                            it.copy(
                                pets = pets,
                                isLoading = false,
                                error = null
                            )
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = exception.message ?: "Error al cargar mascotas"
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Error al cargar mascotas"
                    )
                }
            }
        }
    }

    fun reportRescue(petId: String) {
        viewModelScope.launch {
            try {
                val result = reportPetRescueUseCase(petId)
                result.fold(
                    onSuccess = { operationResult ->
                        when (operationResult) {
                            is OperationResult.Success -> {
                                loadPets() // Recargar la lista después de un rescate exitoso
                            }
                            is OperationResult.Error -> {
                                _uiState.update {
                                    it.copy(error = operationResult.message)
                                }
                            }
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update {
                            it.copy(error = exception.message ?: "Error al reportar rescate")
                        }
                    }
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message ?: "Error al reportar rescate")
                }
            }
        }
    }

    fun reportSighting(petId: String) {
        viewModelScope.launch {
            try {
                val result = reportPetSightingUseCase(petId)
                result.fold(
                    onSuccess = { operationResult ->
                        when (operationResult) {
                            is OperationResult.Success -> {
                                loadPets() // Recargar la lista después de un avistamiento exitoso
                            }
                            is OperationResult.Error -> {
                                _uiState.update {
                                    it.copy(error = operationResult.message)
                                }
                            }
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update {
                            it.copy(error = exception.message ?: "Error al reportar avistamiento")
                        }
                    }
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message ?: "Error al reportar avistamiento")
                }
            }
        }
    }
}