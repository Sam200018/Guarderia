package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.ui.theme.GeneralColor

@Composable
fun OnSaveDialog(openSaveDayReportDialog: MutableState<Boolean>, reportCarerViewModel: ReportCarerViewModel) {


    if (openSaveDayReportDialog.value) {
        AlertDialog(onDismissRequest = { openSaveDayReportDialog.value = false }, title = {
            Text(text = "Seguro que desea guardar el reporte del dia?", fontSize = 20.sp)
        }, text = {
            Text(text = "Esta información ya no será modificable", color = Color.Red, fontSize = 20.sp)
        }, buttons = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = {
                        openSaveDayReportDialog.value = false
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(text = "Cancelar")
                }

                Button(colors = ButtonDefaults.buttonColors(
                    backgroundColor = GeneralColor
                ), onClick = {
                  reportCarerViewModel.saveDayReport()
                    openSaveDayReportDialog.value=false
                }) {
                    Text(text = "Aceptar")
                }

            }
        }
        )
    }
}