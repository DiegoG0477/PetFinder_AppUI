package com.project.petfinder.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petfinder.login.domain.repository.AuthRepository
import com.project.petfinder.login.domain.model.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                when (val result = authRepository.login(_uiState.value.email, _uiState.value.password)) {
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
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)