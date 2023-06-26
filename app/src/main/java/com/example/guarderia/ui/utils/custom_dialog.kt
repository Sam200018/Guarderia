package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.guarderia.domain.entities.Food
import com.example.guarderia.ui.theme.GeneralColor


@Composable
fun CustomDialog(openDialog: MutableState<Boolean>) {
    val selectedFood = remember { mutableStateOf("") }
    val selectedCode = remember { mutableStateOf("") }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                selectedFood.value = ""
                selectedCode.value = ""
                openDialog.value = false
            },
            title = {
                Text(text = "Cual es las comida que desea agregar?")
            },
            text = {
                val expanded = remember { mutableStateOf(false) }
                val expandedCodes = remember { mutableStateOf(false) }
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
                        if (selectedFood.value == "") {
                            TypeDropMenu(type = "Comidas")
                        } else {
                            Text(text = selectedFood.value)
                        }
                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                    ) {
                        DropdownMenuItem(onClick = {
                            selectedFood.value = "Comida 1"
                            expanded.value = false
                        }) {
                            Text("Comida 1")
                        }
                        DropdownMenuItem(onClick = {
                            selectedFood.value = "Comida 2"
                            expanded.value = false
                        }) {
                            Text("Comida 2")
                        }
                    }

                    TextButton(
                        onClick = {
                            expandedCodes.value = !expandedCodes.value
                            expanded.value = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.LightGray,
                        ),
                    ) {
                        if (selectedCode.value == "") {
                            TypeDropMenu(type = "Status")
                        } else {
                            Text(text = selectedCode.value)
                        }
                    }
                    DropdownMenu(
                        expanded = expandedCodes.value,
                        onDismissRequest = { expandedCodes.value = false },
                    ) {
                        DropdownMenuItem(onClick = {
                            selectedCode.value = "CT (Comio Todo)"
                            expandedCodes.value = false
                        }) {
                            Text("CT (Comio Todo)")
                        }
                        DropdownMenuItem(onClick = {
                            selectedCode.value = "NT (No Termino)"
                            expandedCodes.value = false
                        }) {
                            Text("NT (No Termino)")
                        }
                        DropdownMenuItem(onClick = {
                            selectedCode.value = "NQN (No Quiso Nada)"
                            expandedCodes.value = false
                        }) {
                            Text("NQN (No Quiso Nada)")
                        }
                    }
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red
                    ), onClick = { openDialog.value = false }) {
                        Text(text = "Cancelar")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = GeneralColor
                        ),
                        enabled = (selectedCode.value != "" && selectedFood.value != ""),
                        onClick = {
                            val newFood = Food(selectedFood.value, selectedCode.value)

                            selectedCode.value = ""
                            selectedFood.value = ""
                            openDialog.value = false
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        )
    }
}


@Composable
fun TypeDropMenu(type: String) {
    Row {
        Text(text = type)
        Icon(Icons.Filled.ArrowDropDown, "Drop menu about $type")
    }
}
