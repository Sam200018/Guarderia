package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomDialog(openDialog:MutableState<Boolean>) {

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Cual es las comida que desea agregar?")
            },
            text = {
                var expanded = remember { mutableStateOf(false) }
                var expandedCodes = remember { mutableStateOf(false) }
                Column() {

                    TextButton(
                        onClick = {
                            expanded.value = !expanded.value
                            expandedCodes.value = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.LightGray,
                        ),
                    ) {
                        Text(text = "Comidas")
                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                    ) {
                        DropdownMenuItem(onClick = { expanded.value = false }) {
                            Text("Comida 1")
                        }
                        DropdownMenuItem(onClick = { expanded.value = false }) {
                            Text("Comida 2")
                        }
                    }

                    TextButton(
                        onClick = {
                            expanded.value = false
                            expandedCodes.value = !expandedCodes.value
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.LightGray,
                        ),
                    ) {
                        Text(text = "Status")
                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                    ) {
                        DropdownMenuItem(onClick = { expanded.value = false }) {
                            Text("CT (Comio Todo)")
                        }
                        DropdownMenuItem(onClick = { expanded.value = false }) {
                            Text("NT (No Termino)")
                        }
                        DropdownMenuItem(onClick = { expanded.value = false }) {
                            Text("NQN (No Quiso Nada)")
                        }
                    }
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        )
    }

}
