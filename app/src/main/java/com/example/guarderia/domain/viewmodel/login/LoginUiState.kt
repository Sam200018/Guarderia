package com.example.guarderia.domain.viewmodel.login

data class LoginUiState(
    val isEmailValid:Boolean= true,
    val isPasswordValid:Boolean= true,
    val isPasswordVisible: Boolean= false,
    val isFormSubmitting: Boolean = false,
    val isFailure: Boolean = false,
    val errorMessage:String= "",
    val isSuccess: Boolean = false,
    val isFailureConnection: Boolean= false,
)
