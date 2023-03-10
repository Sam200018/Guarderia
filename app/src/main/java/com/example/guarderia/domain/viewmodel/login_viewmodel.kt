package com.example.guarderia.domain.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.User
import com.example.guarderia.ui.routes.Routes


val users: HashMap<String, User> = hashMapOf(
    "samuelbaubas@gmail.com" to User("Samuel", "tutor@", "sabb2006", "5555555555"),
    "hpelayoc@gmail.com" to User("Carlos", "tutor@", "cahp2707", "5555555555"),
    "pruebatutor@mail.com" to User("Pablo", "tutor@", "cont1234", "5555555555"),
    "brendamateos.prim@gmail.com" to User("Brenda", "teacher", "sabb2006", "5555555555"),
    //"teacher1@mail.com" to User("Sofia", "teacher", "cont1234", "5555555555"),
    "pruebaprofe@mail.com" to User("Emanuel", "teacher", "cont1234", "5555555555")
)


class LoginViewModel(private val navigator: NavHostController) : ViewModel() {
    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email
    private val _isEmailValid = MutableLiveData(true)
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password
    private val _isPasswordValid = MutableLiveData(true)
    val isPasswordValid: LiveData<Boolean> = _isPasswordValid

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isPasswordVisible = MutableLiveData(false)
    val isPasswordVisible: LiveData<Boolean> = _isPasswordVisible

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    fun onLoginChange(email: String) {
        _email.value = email
        _isEmailValid.value = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _isLoginEnable.value = enableLogin(email, _password.value!!)
    }


    fun onPasswordChange(password: String) {
        _password.value = password
        _isPasswordValid.value = password.length > 6
        _isLoginEnable.value = enableLogin(_email.value!!, password)
    }

    fun onPasswordVisibilityChange() {
        _isPasswordVisible.value = !(_isPasswordVisible.value)!!
    }

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun login() {

        val userKey = users.get(key = _email.value)
        if (userKey != null) {
            if (userKey.password == _password.value) {

                if(userKey.type=="tutor@") {

                    navigator.navigate("${Routes.ChildrenScreen.route}/${_email.value}/${userKey.type}") {

                        popUpTo(Routes.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
                else{
                    navigator.navigate("${Routes.GroupSelectionScreen.route}/${_email.value}/${userKey.type}"){
                        popUpTo(Routes.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                _errorMessage.value = "Error! Credenciales incorrectas"

            }
        } else {
            _errorMessage.value = "Error! Usario no existe"
        }


    }
}