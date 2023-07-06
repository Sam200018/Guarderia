package com.example.guarderia.domain.viewmodel.auth

import com.example.guarderia.model.User


enum class AuthStatus {
    Checking,
    Authenticated,
    Unauthenticated
}


data class AuthUiState(
    val authStatus: AuthStatus = AuthStatus.Checking,
    val errorMessage: String = "",
    val user: User?
)
