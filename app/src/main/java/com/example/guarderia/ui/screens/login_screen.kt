package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guarderia.ui.theme.GeneralColor

@Preview(
    showBackground = true
)
@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Body(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun Body(
    modifier: Modifier
) {
    Column(modifier = modifier) {
        LogoImage()
        Spacer(modifier = Modifier.size(95.dp))
        UserInput(
            onTextChange = {
                println(it)
            }
        )
        Spacer(modifier = Modifier.size(40.dp))
        PasswordInput(onTextChange = {
            println(it)
        })
        Spacer(modifier = Modifier.size(5.dp))
        ForgetPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(20.dp))
        LoginButton(Modifier.align(Alignment.CenterHorizontally))
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
fun UserInput(onTextChange: (String) -> Unit) {
    TextField(value = "", onValueChange = onTextChange, modifier = Modifier.fillMaxWidth(), label = {
        Text(text = "Usuario")
    })
}

@Composable
fun PasswordInput(onTextChange: (String) -> Unit) {
    TextField(value = "", onValueChange = onTextChange, modifier = Modifier.fillMaxWidth(), label = {
        Text(text = "Contraseña")
    })
}

@Composable
fun ForgetPassword(modifier: Modifier) {
    TextButton(onClick = { /*TODO*/ }, modifier = modifier, colors = ButtonDefaults.buttonColors(
        contentColor = GeneralColor,
        backgroundColor = Color.Transparent
    )) {
        Text(text = "¿Olvidaste tu contraseña?", textDecoration = TextDecoration.Underline)

    }

}

@Composable
fun LoginButton(modifier: Modifier) {
    Button(
        onClick = {
            println("login")
        },
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