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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guarderia.R
import com.example.guarderia.domain.viewmodel.auth.AuthViewModel
import com.example.guarderia.domain.viewmodel.login.LoginViewModel
import com.example.guarderia.ui.theme.GeneralColor
import com.example.guarderia.ui.utils.TextFieldError

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    authViewModel: AuthViewModel,
    ) {
    //Ui State
    val loginUiState by loginViewModel.uiState.collectAsState()


    val isLoginEnable = (loginUiState.isEmailValid && loginUiState.isPasswordValid)
    val isNotEmptyForm =
        (loginViewModel.emailInput.isNotEmpty() && loginViewModel.passwordInput.isNotEmpty())
    val isLoginError = loginUiState.isFailure


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        LogoImage()
        Spacer(modifier = Modifier.size(95.dp))
        UserInput(loginViewModel.emailInput, loginUiState.isEmailValid) {
            loginViewModel.onEmailChange(it)
        }
        Spacer(modifier = Modifier.size(40.dp))
        PasswordInput(
            loginViewModel,
            loginViewModel.passwordInput,
            loginUiState.isPasswordVisible,
            loginUiState.isPasswordValid
        ) {
            loginViewModel.onPasswordChange(it)
        }
        Spacer(modifier = Modifier.size(5.dp))
        ForgetPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(20.dp))
        LoginButton(
            Modifier.align(Alignment.CenterHorizontally),
            isEnable = isLoginEnable && isNotEmptyForm && !isLoginError,
            isFailure = isLoginError,
            loginClick = {
                authViewModel.login(loginViewModel.emailInput, loginViewModel.passwordInput)
            },
            errorMessage = loginUiState.errorMessage,
            isLoading = loginUiState.isFormSubmitting
        )

    }
}

@Composable
fun LogoImage() {

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = stringResource(id = R.string.welcome_message), fontSize = 30.sp)
    }
}

@Composable
fun UserInput(email: String, isEmailValid: Boolean, onTextChange: (String) -> Unit) {
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = onTextChange,
            isError = !isEmailValid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.email_input_label))
            },

            )
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

    OutlinedTextField(
        value = password,
        isError = !isPasswordValid,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
        label = {
            Text(text = stringResource(id = R.string.password_input_label))
        },
        visualTransformation = if (isPasswordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )

}

@Composable
fun ForgetPassword(modifier: Modifier) {
    TextButton(
        onClick = { /*TODO*/ }, modifier = modifier, colors = ButtonDefaults.buttonColors(
            contentColor = GeneralColor,
            backgroundColor = Color.Transparent
        )
    ) {
        Text(
            text = stringResource(id = R.string.forgot_password),
            textDecoration = TextDecoration.Underline
        )

    }

}

@Composable
fun LoginButton(
    modifier: Modifier,
    isEnable: Boolean,
    isFailure: Boolean,
    loginClick: () -> Unit,
    errorMessage: String,
    isLoading: Boolean
) {
    Column(modifier) {

        if (isLoading) {
            CircularProgressIndicator()
        } else {
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
                Text(text = stringResource(id = R.string.login_button_label), fontSize = 24.sp)
            }
        }
        if (errorMessage.isNotEmpty() && isFailure) {
            TextFieldError(errorMessage)
        }

    }

}