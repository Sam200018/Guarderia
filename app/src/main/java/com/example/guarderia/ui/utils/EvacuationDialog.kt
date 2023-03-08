package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.guarderia.domain.entities.Evacuation
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.ui.theme.GeneralColor
import java.util.*


@Composable
fun EvacuationDialog(openEvacuationDialog: MutableState<Boolean>, reportCarerViewModel: ReportCarerViewModel) {
    val typeSelected = remember { mutableStateOf("") }

    if (openEvacuationDialog.value) {
        AlertDialog(
            modifier = Modifier.padding(10.dp),
            onDismissRequest = { openEvacuationDialog.value = false },
            title = {
                Text(text = "Que tipo de evacuación desea registrar?")
            },
            text = {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    TextButton(modifier = Modifier.size(110.dp, 50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = backgroundColor(typeSelected, "pipi")
                        ), onClick = {
                            typeSelected.value = "pipi"
                        }) {
                        Text(text = "Pipí")
                    }
                    TextButton(
                        modifier = Modifier.size(110.dp, 50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = backgroundColor(typeSelected, "popo")
                        ),
                        onClick = {
                            typeSelected.value = "popo"
                        }) {
                        Text(text = "Popó")
                    }
                }
            },
            buttons = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(
                        onClick = {
                            openEvacuationDialog.value = false
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text(text = "Cancelar")
                    }

                    Button(colors = ButtonDefaults.buttonColors(
                        backgroundColor = GeneralColor
                    ), onClick = {
                        val newEvacuation = Evacuation("", Date())
                        reportCarerViewModel.addFoodEvacuationReport(newEvacuation)
                        openEvacuationDialog.value = false
                    }) {
                        Text(text = "Aceptar")
                    }

                }
            })
    }
}

fun backgroundColor(typeSelected: MutableState<String>, s: String): Color {
    return if (typeSelected.value == s) {
        Color.Green
    } else {
        Color.LightGray
    }
}
