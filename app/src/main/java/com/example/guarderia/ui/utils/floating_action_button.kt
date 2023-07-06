package com.example.guarderia.ui.utils

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.guarderia.R
import com.example.guarderia.ui.routes.Routes

@Composable
fun GuarderiaFloatingActionButton(navController: NavHostController, nextRoute: String) {
    FloatingActionButton(onClick = {
        val nextScreen = when (nextRoute) {
            "home" -> Routes.AddNotice
            else -> Routes.Home
        }
        navController.navigate(nextScreen.route)
    }, backgroundColor = colorResource(id = R.color.purple_700)) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}