package com.project.petfinder.features.register.presentation.viewmodel

import com.project.petfinder.core.domain.model.Municipality

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