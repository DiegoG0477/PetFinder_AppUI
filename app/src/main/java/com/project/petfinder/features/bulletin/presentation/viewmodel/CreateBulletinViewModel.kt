package com.project.petfinder.features.bulletin.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.Municipality
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.core.domain.usecase.GetMunicipalitiesUseCase
import com.project.petfinder.features.bulletin.domain.usecase.CreateBulletinUseCase
import com.project.petfinder.features.bulletin.domain.usecase.ValidateBulletinInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class CreateBulletinViewModel @Inject constructor(
    private val getMunicipalitiesUseCase: GetMunicipalitiesUseCase,
    private val createBulletinUseCase: CreateBulletinUseCase,
    private val validateBulletinInputUseCase: ValidateBulletinInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateBulletinUiState())
    val uiState: StateFlow<CreateBulletinUiState> = _uiState

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
        val currentState = _uiState.value

        when (val validationResult = validateBulletinInputUseCase(
            currentState.petName,
            currentState.selectedMunicipality,
            currentState.selectedImageUri
        )) {
            is OperationResult.Error -> {
                _uiState.value = currentState.copy(errorMessage = validationResult.message)
                return
            }
            is OperationResult.Success -> {
                submitBulletin()
            }
        }
    }

    private fun submitBulletin() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                val result = createBulletinUseCase(
                    petName = _uiState.value.petName,
                    date = _uiState.value.date,
                    municipalityId = (_uiState.value.selectedMunicipality?.id ?: "").toString(),
                    additionalInfo = _uiState.value.additionalInfo,
                    imageUri = _uiState.value.selectedImageUri!!
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
                            errorMessage = exception.message ?: "Error al crear el boletín"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error al crear el boletín"
                )
            }
        }
    }
}