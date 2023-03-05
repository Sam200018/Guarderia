package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guarderia.domain.viewmodel.LoginViewModel
import com.example.guarderia.ui.theme.GeneralColor
import com.example.guarderia.ui.utils.TextFieldError

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Body(modifier = Modifier.align(Alignment.Center), loginViewModel)
    }
}

@Composable
fun Body(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
) {


    val email: String by loginViewModel.email.observeAsState(initial = "")
    val isEmailValid: Boolean by loginViewModel.isEmailValid.observeAsState(true)
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isPasswordValid: Boolean by loginViewModel.isPasswordValid.observeAsState(true)
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)
    val isPasswordVisible: Boolean by loginViewModel.isPasswordVisible.observeAsState(initial = false)
    val errorMessage: String by loginViewModel.errorMessage.observeAsState("")


    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        LogoImage()
        Spacer(modifier = Modifier.size(95.dp))
        UserInput(email, isEmailValid) {
            loginViewModel.onLoginChange(it)
        }
        Spacer(modifier = Modifier.size(40.dp))
        PasswordInput(loginViewModel, password, isPasswordVisible, isPasswordValid) {
            loginViewModel.onPasswordChange(it)
        }
        Spacer(modifier = Modifier.size(5.dp))
        ForgetPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(20.dp))
        LoginButton(
            Modifier.align(Alignment.CenterHorizontally),
            isEnable = isLoginEnable,
            loginClick = {
                loginViewModel.login()
//                reportCarerViewMode(

            },
            errorMessage = errorMessage
        )

    }
}

@Composable
fun LogoImage() {

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "¡BIENVENIDO!", fontSize = 30.sp)
    }
}

@Composable
fun UserInput(email: String, isEmailValid: Boolean, onTextChange: (String) -> Unit) {
    Column {
        TextField(value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Usuario")
            })
        if (!isEmailValid) {
            TextFieldError(message = "Usuario no valido")
        }
    }
}

@Composable
fun PasswordInput(
    loginViewModel: LoginViewModel,
    password: String,
    isPasswordVisibility: Boolean,
    isPasswordValid: Boolean,
    onTextChange: (String) -> Unit
) {

    Column {
        TextField(value = password,
            visualTransformation = if (isPasswordVisibility) {

                VisualTransformation.None
            } else {
                PasswordVisualTransformation()

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {

                val image = if (isPasswordVisibility) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }

                IconButton(onClick = { loginViewModel.onPasswordVisibilityChange() }) {
                    Icon(
                        image,
                        contentDescription = "Change password visibility"
                    )

                }
            },
//        trailingIcon = PasswordVisibility(loginViewModel),
            label = {
                Text(text = "Contraseña")
            })
        if (!isPasswordValid) {
            TextFieldError(message = "Contraseña no valida")
        }

    }
}

@Composable
fun ForgetPassword(modifier: Modifier) {
    TextButton(
        onClick = { /*TODO*/ }, modifier = modifier, colors = ButtonDefaults.buttonColors(
            contentColor = GeneralColor,
            backgroundColor = Color.Transparent
        )
    ) {
        Text(text = "¿Olvidaste tu contraseña?", textDecoration = TextDecoration.Underline)

    }

}

@Composable
fun LoginButton(modifier: Modifier, isEnable: Boolean, loginClick: () -> Unit, errorMessage: String) {
    Column(modifier) {

        Button(
            enabled = isEnable,
            onClick = loginClick,
            modifier = modifier
                .size(width = 250.dp, height = 50.dp)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = GeneralColor
            )

        ) {
            Text(text = "Iniciar sesión", fontSize = 24.sp)
        }
        if (errorMessage.isNotEmpty()) {
            TextFieldError(errorMessage)
        }

    }

}