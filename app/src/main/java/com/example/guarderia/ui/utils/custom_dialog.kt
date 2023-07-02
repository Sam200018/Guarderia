package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.guarderia.R
import com.example.guarderia.domain.viewmodel.foodRegister.FoodRegisterViewModel
import com.example.guarderia.model.Food
import com.example.guarderia.model.Ingestion
import kotlinx.coroutines.runBlocking


@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean>,
    ingestion: Ingestion,
    food: Food,
    foodRegisterViewModel: FoodRegisterViewModel,
    type: String
) {
    val selectedOption = remember { mutableStateOf(ingestion.gratification) }


    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "${ingestion.firstSurname} ${ingestion.secondSurname} ${ingestion.name} ")
            },
            text = {
                val foodGratifications = listOf(
                    stringResource(id = R.string.eatAll),
                    stringResource(id = R.string.eatFew),
                    stringResource(id = R.string.notEat),
                    stringResource(id = R.string.absent),
                )
                Column() {
                    foodGratifications.forEachIndexed { index, gratification ->
                        Row(
                            modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            RadioButton(
                                selected = index + 1 == selectedOption.value,
                                onClick = { selectedOption.value = index + 1 })
                            Text(text = gratification)
                            IngestionIcon(gratification = index + 1) {
                                selectedOption.value = index + 1
                            }
                        }
                    }

                }
            },
            buttons = {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.green_button)
                        ),
                        onClick = {
                            runBlocking {
                                foodRegisterViewModel.recordIngestion(
                                    ingestion,
                                    selectedOption.value,
                                    ingestion.childId,
                                    food.id
                                )
                                foodRegisterViewModel.getIngestions(type)
                                openDialog.value = false

                            }
                        }
                    ) {
                        Text("Aceptar")
                    }
                    Button(colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red
                    ), onClick = { openDialog.value = false }) {
                        Text(text = "Cancelar")
                    }
                }
            }
        )
    }
}


@Composable
fun IngestionIcon(gratification: Int, onChangeSelection: () -> Unit) {
    val backgroundColor = when (gratification) {
        1 -> colorResource(id = R.color.green_button_disable)
        2 -> colorResource(id = R.color.eatingFewColor)
        3 -> colorResource(id = R.color.notEatingColor)
        4 -> colorResource(id = R.color.absentColor)
        else -> {
            colorResource(id = R.color.unknown)
        }
    }
    Button(
        onClick = onChangeSelection, colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        )
    ) {

        Icon(Icons.Default.Face, contentDescription = null)
    }
}