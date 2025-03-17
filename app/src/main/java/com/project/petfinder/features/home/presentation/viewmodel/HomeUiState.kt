package com.project.petfinder.features.home.presentation.viewmodel

import com.project.petfinder.features.home.domain.model.Pet

data class HomeUiState(
    val pets: List<Pet> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)