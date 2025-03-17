package com.project.petfinder.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.core.domain.model.OperationResult
import com.project.petfinder.features.login.domain.model.AuthResult
import com.project.petfinder.features.login.domain.usecase.LoginUseCase
import com.project.petfinder.features.login.domain.usecase.ValidateLoginCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateLoginCredentialsUseCase: ValidateLoginCredentialsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

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

    fun login() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                // Primero validamos las credenciales
                when (val validationResult = validateLoginCredentialsUseCase(
                    _uiState.value.email,
                    _uiState.value.password
                )) {
                    is OperationResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = validationResult.message
                        )
                        return@launch
                    }
                    is OperationResult.Success -> {
                        // Continuamos con el login si la validación es exitosa
                        processLogin()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error desconocido al iniciar sesión"
                )
            }
        }
    }

    private suspend fun processLogin() {
        try {
            when (val result = loginUseCase(_uiState.value.email, _uiState.value.password)) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        errorMessage = null
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSuccess = false,
                        errorMessage = result.errorMessage
                    )
                }
            }
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isSuccess = false,
                errorMessage = e.message ?: "Error desconocido al iniciar sesión"
            )
        }
    }
}