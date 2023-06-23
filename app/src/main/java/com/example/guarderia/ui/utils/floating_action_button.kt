package com.example.guarderia.ui.utils

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.guarderia.R

@Composable
fun GuarderiaFloatingActionButton() {
    FloatingActionButton(onClick = {

    }, backgroundColor = colorResource(id = R.color.purple_700)) {
        Icon(Icons.Filled.Add,contentDescription = "")
    }
}