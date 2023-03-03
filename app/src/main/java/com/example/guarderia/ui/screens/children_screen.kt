package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChildrenScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text("Selection screen")
    }
}