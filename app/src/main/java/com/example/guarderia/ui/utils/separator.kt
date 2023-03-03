package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guarderia.ui.theme.GeneralColor

@Composable
fun Separator(text:String){
    Column {
        Text(text, color = GeneralColor, fontSize = 24.sp)
        Divider(color = GeneralColor, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
    }
}