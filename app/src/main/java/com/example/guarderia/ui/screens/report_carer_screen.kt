package com.example.guarderia.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.Food
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.ui.theme.GeneralColor
import com.example.guarderia.ui.utils.CustomDialog
import com.example.guarderia.ui.utils.RowAction
import com.example.guarderia.ui.utils.SelectedDate
import com.example.guarderia.ui.utils.Separator
import java.util.Date

@Composable
fun ReportCarerScreen(reportCarerViewModel: ReportCarerViewModel, navigator: NavHostController) {

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
                        navigator.popBackStack()
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
                modifier = Modifier
                    .size(166.dp, 50.dp)
                    .clip(CircleShape),
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val context = LocalContext.current

            Body(context, reportCarerViewModel)

        }
    }
}

@Composable
fun Body(context: Context, reportCarerViewModel: ReportCarerViewModel) {
    val scope= rememberCoroutineScope()
    val openSaveDayReportDialog = remember { mutableStateOf(false) }
    val openAddFoodReportDialog = remember { mutableStateOf(false) }

    val selectedChild = reportCarerViewModel.child
    val father = reportCarerViewModel.father
    val isEnable: Boolean by reportCarerViewModel.isEditable.observeAsState(true)
    val date: Date by reportCarerViewModel.date.observeAsState(Date())
    val foodReport = reportCarerViewModel.foodReport



    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomDialog(openDialog = openAddFoodReportDialog,reportCarerViewModel)
        Text(selectedChild!!.name, fontSize = 24.sp)
        Spacer(Modifier.size(10.dp))
        Text("Edad: ${selectedChild.age} años", fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        Text(father!!.name, fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        Text(father.phone, fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        SelectedDate(context, reportCarerViewModel, date)
        Spacer(Modifier.size(15.dp))
        Separator("Reporte De Comida Ingerida")
        FoodTable(isEnable, foodReport, dialog = {
            openAddFoodReportDialog.value = true
        })
        Separator("Reporte De Evacuaciones")
        EvacuationTable(isEnable)
        Separator("Observaciones")
        Spacer(Modifier.size(10.dp))
        Details()
    }
}


@Composable
fun FoodTable(isEnable: Boolean, foodReport: List<Food>, dialog: () -> Unit) {
    val listState = rememberLazyListState(0)


    LazyColumn(state = listState) {
        item {
            RowAction("Comida", "Status", GeneralColor, isEnable, action = dialog) {
                Icon(Icons.Filled.Add, contentDescription = "Add element ")
            }
        }
        if (foodReport.isEmpty()) {
            item {
                Text(text = "No has agregado ningun registro", textAlign = TextAlign.Center)
            }
        } else {
            items(foodReport.size) {
                RowAction(
                    food = foodReport[it].food,
                    description = foodReport[it].status,
                    color = Color.Gray,
                    isEnable = isEnable,
                    action = {
                        Log.i("Item rowAction", "$it")
                    }) {
                    Icon(Icons.Filled.NoteAdd, "Edit food Report")
                }
            }
        }
    }
}

@Composable
fun EvacuationTable(isEnable: Boolean) {
    val listState = rememberLazyListState(0)
    LazyColumn(state = listState) {
        item {
            RowAction("Evacuación", "Hora", GeneralColor, isEnable, action = {}) {
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