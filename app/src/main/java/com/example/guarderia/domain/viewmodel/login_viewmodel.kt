package com.example.guarderia.domain.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guarderia.ui.routes.Routes

class LoginViewModel(private val navigator: NavHostController) : ViewModel() {
    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    fun onLoginChange(email: String) {
        _email.value = email
        _isLoginEnable.value = enableLogin(email, _password.value!!)
    }


    fun onPasswordChange(password: String) {
        _password.value = password
        _isLoginEnable.value = enableLogin(_email.value!!, password)
    }

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun login() {
        navigator.navigate(Routes.ChildrenScreen.route)
        println("Login")
    }
}