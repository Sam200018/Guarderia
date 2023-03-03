package com.example.guarderia.ui.screens

import android.app.DatePickerDialog
import android.content.ClipData.Item
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guarderia.ui.theme.GeneralColor
import com.example.guarderia.ui.utils.RowAction
import com.example.guarderia.ui.utils.SelectedDate
import com.example.guarderia.ui.utils.Separator
import java.util.Calendar
import java.util.Date

@Preview(showBackground = true)
@Composable
fun ReportCarerScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(contentAlignment = Alignment.Center) {

                        Text("Reporte Diario", fontSize = 24.sp, textAlign = TextAlign.Center)
                    }
                },
                navigationIcon = {

                    IconButton(onClick = {
//                        navigator.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "BackNavigation")
                    }
                },
                backgroundColor = GeneralColor
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Button(
                onClick = {},
                modifier = Modifier.size(166.dp, 50.dp).clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = GeneralColor
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Guardar", fontSize = 16.sp)
                    Icon(Icons.Filled.Save, contentDescription = "Save button")
                }
            }
        }
    ) {

        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            val context = LocalContext.current

            Body(context)

        }
    }
}

@Composable
fun Body(context: Context) {
    Column(modifier = Modifier.padding(20.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Eliu Eduardo Mendoza", fontSize = 24.sp)
        Spacer(Modifier.size(10.dp))
        Text("Edad: 5 años", fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        Text("Tutor@: Ulises Velez Saldania ", fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        Text("Telefono: ", fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        SelectedDate(context)
        Spacer(Modifier.size(15.dp))
        Separator("Reporte De Comida Ingerida")
        FoodTable()
        Separator("Reporte De Evacuaciones")
        EvacuationTable()
        Separator("Observaciones")
        Spacer(Modifier.size(10.dp))
        Details()
    }
}



@Composable
fun FoodTable() {
    val listState = rememberLazyListState(0)

    LazyColumn(state = listState) {

        item {
            RowAction("Comida", "Status", GeneralColor, action = {}) {
                Icon(Icons.Filled.Add, contentDescription = "Add element ")
            }
        }

    }
}

@Composable
fun EvacuationTable() {
    val listState = rememberLazyListState(0)
    LazyColumn(state = listState) {
        item {
            RowAction("Evacuación", "Hora", GeneralColor, action = {}) {
                Icon(Icons.Filled.Add, contentDescription = "Add element ")
            }
        }

    }
}

@Composable
fun Details() {
    TextField(modifier = Modifier.defaultMinSize(361.dp, 239.dp), value = "", onValueChange = {}, label = {
        Text("Obeservaciones")
    })
}