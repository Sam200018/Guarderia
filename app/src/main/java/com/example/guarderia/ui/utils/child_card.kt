package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.*
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
import com.example.guarderia.domain.entities.Child


@Composable
fun ChildCard(
    child: Child,
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
            Text(text = child.name, fontSize = 20.sp)
            Icon(Icons.Filled.ArrowRight, "Go to information of the selected child", Modifier.size(30.dp))
        }
    }
}