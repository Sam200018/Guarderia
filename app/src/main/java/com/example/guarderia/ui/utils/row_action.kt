package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun RowAction(food: String, description: String, color:Color, action: () -> Unit, iconAction: @Composable () -> Unit) {


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(food, fontSize = 20.sp)
        Text(description, fontSize = 20.sp)
        TextButton(onClick = action, colors = ButtonDefaults.buttonColors(
            backgroundColor = color
        )) {
            iconAction()
        }

    }

}