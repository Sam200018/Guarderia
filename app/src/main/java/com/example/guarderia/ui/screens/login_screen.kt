package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guarderia.domain.viewmodel.LoginViewModel
import com.example.guarderia.ui.theme.GeneralColor


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
    loginViewModel: LoginViewModel
) {

    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)


    Column(modifier = modifier) {
        LogoImage()
        Spacer(modifier = Modifier.size(95.dp))
        UserInput(email) {
            loginViewModel.onLoginChange(email)
        }
        Spacer(modifier = Modifier.size(40.dp))
        PasswordInput(password) {
            loginViewModel.onPasswordChange(password)
        }
        Spacer(modifier = Modifier.size(5.dp))
        ForgetPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(20.dp))
        LoginButton(Modifier.align(Alignment.CenterHorizontally), isLoginEnable) {
            loginViewModel.login()
        }

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
fun UserInput(email: String, onTextChange: (String) -> Unit) {
    TextField(value = email, onValueChange = onTextChange, modifier = Modifier.fillMaxWidth(), label = {
        Text(text = "Usuario")
    })
}

@Composable
fun PasswordInput(password: String, onTextChange: (String) -> Unit) {
    TextField(value = password, onValueChange = onTextChange, modifier = Modifier.fillMaxWidth(), label = {
        Text(text = "Contraseña")
    })
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
fun LoginButton(modifier: Modifier, isEnable: Boolean, loginClick: () -> Unit) {
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
}