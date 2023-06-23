package com.example.guarderia.ui.utils

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.guarderia.R
import com.example.guarderia.ui.routes.GuarderiaRoutes

@Composable
fun GuarderiaFloatingActionButton(navController: NavHostController, nextRoute: String) {
    FloatingActionButton(onClick = {
        val nextScreen = when (nextRoute) {
            "Home" -> GuarderiaRoutes.AddNotice
            else -> GuarderiaRoutes.Home
        }
        navController.navigate(nextScreen.name)
    }, backgroundColor = colorResource(id = R.color.purple_700)) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}