package com.example.guarderia.ui.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.guarderia.ui.theme.FilterButtonColor
import com.example.guarderia.ui.theme.FilterButtonDisableColor

@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    action: () -> Unit
) {
    TextButton(
        onClick =action,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected) {
                FilterButtonColor
            } else {
                FilterButtonDisableColor
            },
            contentColor = if (selected) {
                Color.White
            } else {
                Color.Black
            },
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = text)
    }
}