package com.project.petfinder.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.home.domain.model.Pet
import com.project.petfinder.home.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val petRepository: PetRepository
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
                val result = petRepository.getLostPets()
                val pets = result.getOrElse {
                    throw it
                }
                _uiState.update {
                    it.copy(
                        pets = pets,
                        isLoading = false,
                        error = null
                    )
                }
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
                petRepository.reportRescue(petId)
                // You might want to reload pets or handle UI update
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
                petRepository.reportSighting(petId)
                // You might want to reload pets or handle UI update
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message ?: "Error al reportar avistamiento")
                }
            }
        }
    }
}

data class HomeUiState(
    val pets: List<Pet> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)