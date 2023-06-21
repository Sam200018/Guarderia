package com.example.guarderia.domain.viewmodel.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.guarderia.R
import com.example.guarderia.domain.entities.User
import com.example.guarderia.ui.routes.GuarderiaRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


val users: HashMap<String, User> = hashMapOf(
    "samuelbaubas@gmail.com" to User("Samuel", "tutor@", "sabb2006", "5555555555"),
    "hpelayoc@gmail.com" to User("Carlos", "tutor@", "cahp2707", "5555555555"),
    "pruebatutor@mail.com" to User("Pablo", "tutor@", "cont1234", "5555555555"),
    "brendamateos.prim@gmail.com" to User("Brenda", "teacher", "sabb2006", "5555555555"),
    //"teacher1@mail.com" to User("Sofia", "teacher", "cont1234", "5555555555"),
    "pruebaprofe@mail.com" to User("Emanuel", "teacher", "cont1234", "5555555555")
)


class LoginViewModel() : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    var emailInput: String by mutableStateOf("")
        //        Override set method to only make it private
        private set

    var passwordInput: String by mutableStateOf("")
        private set

    var type: String by mutableStateOf("")
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

    /**
     *Login using coroutine to simulate a http request
     */
    fun login(navigator: NavHostController) {
        viewModelScope.launch {
//          This indicates the UI the form is submitting
            _uiState.update {
                it.copy(isFormSubmitting = true)
            }

//          The UI waits this time
            delay(2000)

//          Then we look for the user using the email
            val userKey = users.get(key = emailInput)

//            if we got the user we check the password and finally check if it match with the existent on DB
//            to navigate the next page depending on if the user is teacher or tutor@
            if (userKey != null) {
                if (userKey.password == passwordInput) {
                    type = userKey.type

                    _uiState.value = LoginUiState(isSuccess = true)
                    if (type == "tutor@") {
                        navigator.navigate(GuarderiaRoutes.Home.name) {
                            popUpTo(GuarderiaRoutes.Login.name) {
                                inclusive = true
                            }
                        }
                    } else {
                        navigator.navigate(GuarderiaRoutes.GroupSelectionScreen.name) {
                            popUpTo(GuarderiaRoutes.Login.name) {
                                inclusive = true
                            }
                        }
                    }

                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isFailure = true,
                            isFormSubmitting = false,
                            errorMessage = R.string.wrong_credentials.toString()
                        )
                    }
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        isFailure = true,
                        isFormSubmitting = false,
                        errorMessage = R.string.wrong_credentials.toString()
                    )
                }
            }
        }


    }
}