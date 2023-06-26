package com.example.guarderia.domain.viewmodel.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
class LoginViewModel() : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    var emailInput: String by mutableStateOf("")
        //        Override set method to only make it private
        private set

    var passwordInput: String by mutableStateOf("")
        private set

    fun onEmailChange(email: String) {
//      Change here using private set method
        emailInput = email
//      Using the methods of a data class, update the UI using the currentState and immutability concept
        _uiState.update { currentState ->
            currentState.copy(
                isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches(),
                isFailure = false
            )
        }
    }


    fun onPasswordChange(password: String) {
        passwordInput = password
        _uiState.update { currentState ->
            currentState.copy(isPasswordValid = true, isFailure = false)
        }
    }

    fun onPasswordVisibilityChange() {
        _uiState.update { currentState ->
            currentState.copy(isPasswordVisible = !currentState.isPasswordVisible)
        }
    }
}