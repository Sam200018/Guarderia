package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.guarderia.R
import com.example.guarderia.model.Ingestion

@Composable
fun IngestionTab(ingestion: Ingestion, openDialog: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "${ingestion.firstSurname} ${ingestion.secondSurname} ${ingestion.name} ",
            fontSize = 20.sp
        )
        IngestionButton(ingestion.gratification, openDialog)
    }
}

@Composable
fun IngestionButton(gratification: Int, openDialog: () -> Unit) {


    val backgroundColor = when (gratification) {
        1 -> colorResource(id = R.color.green_button_disable)
        2 -> colorResource(id = R.color.eatingFewColor)
        3 -> colorResource(id = R.color.notEatingColor)
        4 -> colorResource(id = R.color.absentColor)
        else -> {
            colorResource(id = R.color.unknown)
        }
    }
    val icon = if (gratification == 0) Icons.Default.QuestionMark else Icons.Default.Face

    Button(
        onClick = openDialog, colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        )
    ) {

        Icon(icon, contentDescription = null)
    }

}