package com.example.guarderia.domain.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guarderia.GuarderiaApplication
import com.example.guarderia.data.AuthRepository
import com.example.guarderia.data.LoginRequest
import com.example.guarderia.data.TokenEntity
import com.example.guarderia.model.AuthResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception


class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState(user = null))
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            checkingStatus()
        }
    }

    fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        viewModelScope.launch {
            try {
                val response = authRepository.login(loginRequest)
                setLoggedUser(response)
            } catch (e: Exception) {
                logout(e.message)
            }
        }
    }

    private suspend fun checkingStatus() {
        val tokenEntity = authRepository.getToken()
        if (tokenEntity == null) {
            return logout(null)
        } else {
            try {
                val response = authRepository.checkAuthStatus(tokenEntity.token)
                setLoggedUser(response)
            } catch (e: Exception) {
                return logout(e.message.toString())
            }

        }

    }

    suspend fun logout(errorMessage: String?) {
//        authRepository.logout()
        authRepository.deleteToken()

        _uiState.value = AuthUiState(
            user = null,
            authStatus = AuthStatus.Unauthenticated,
            errorMessage = errorMessage ?: ""
        )
    }

    private suspend fun setLoggedUser(response: AuthResponse) {
        val tokenLocal = TokenEntity(1, response.token)
        authRepository.saveToken(tokenLocal)

        _uiState.value =
            AuthUiState(
                user = response.user,
                authStatus = AuthStatus.Authenticated,
                errorMessage = ""
            )
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GuarderiaApplication)
                val authRepository = application.container.authRepository
                AuthViewModel(authRepository)
            }
        }
    }
}