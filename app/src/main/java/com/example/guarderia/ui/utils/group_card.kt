package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GroupCard(
    user: String,
    action: () -> Unit
) {
    Button(
        onClick = action, modifier = Modifier.size(441.dp, 63.dp), colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.LightGray
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            if(user=="brendamateos.prim@gmail.com")
                {Text(text = "Grupo Mi Alegría", fontSize = 20.sp)}
            else{
                Text(text = "Grupo El Covenant", fontSize = 20.sp)
            }
            Icon(Icons.Filled.ArrowRight, "Go to group", Modifier.size(30.dp))
        }
    }
}