package com.example.guarderia.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import com.example.guarderia.domain.entities.Evacuation
import com.example.guarderia.domain.entities.Food
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.ui.theme.GeneralColor
import com.example.guarderia.ui.utils.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReportCarerScreen(reportCarerViewModel: ReportCarerViewModel, navigator: NavHostController) {

    val foodReport = reportCarerViewModel.foodReport
    val evacuationReport = reportCarerViewModel.evacuationReport
    val details: String by reportCarerViewModel.details.observeAsState("")
    val openSaveDayReportDialog = remember { mutableStateOf(false) }
    val isEnable: Boolean by reportCarerViewModel.isEditable.observeAsState(true)
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
                        reportCarerViewModel.back()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "BackNavigation")
                    }
                },
                backgroundColor = GeneralColor
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (isEnable) {
                Button(
                    onClick = {
                        openSaveDayReportDialog.value = true
                    },
                    enabled = (evacuationReport.isNotEmpty() && foodReport.isNotEmpty() && details.isNotEmpty()),
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
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val context = LocalContext.current

            Body(
                context,
                isEnable,
                openSaveDayReportDialog,
                reportCarerViewModel,
                foodReport,
                evacuationReport,
                details
            )

        }
    }
}

@Composable
fun Body(
    context: Context,
    isEnable: Boolean,
    openSaveDayReportDialog: MutableState<Boolean>,
    reportCarerViewModel: ReportCarerViewModel,
    foodReport: List<Food>,
    evacuationReport: List<Evacuation>,
    details: String
) {
//    val scope = rememberCoroutineScope()
    val openAddEvacuationReportDialog = remember { mutableStateOf(false) }
    val openAddFoodReportDialog = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val selectedChild = reportCarerViewModel.child
    val father = reportCarerViewModel.father
    val date: Date by reportCarerViewModel.date.observeAsState(Date())



    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally

    ) {
        CustomDialog(openDialog = openAddFoodReportDialog, reportCarerViewModel)
        EvacuationDialog(openEvacuationDialog = openAddEvacuationReportDialog, reportCarerViewModel)
        OnSaveDialog(openSaveDayReportDialog, reportCarerViewModel)
        Text(selectedChild!!.name, fontSize = 24.sp)
        Spacer(Modifier.size(10.dp))
        Text("Edad: ${selectedChild.age} años", fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        Text(father!!.name, fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        Text(father.phone, fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        SelectedDate(context, date){
            reportCarerViewModel.changeDate(it)
        }
        Spacer(Modifier.size(15.dp))
        Separator("Reporte De Comida Ingerida")
        FoodTable(isEnable, foodReport, dialog = {
            openAddFoodReportDialog.value = true
        })
        Separator("Reporte De Evacuaciones")
        EvacuationTable(isEnable, evacuationReport) {
            openAddEvacuationReportDialog.value = true
        }
        Separator("Observaciones")
        Spacer(Modifier.size(10.dp))
        Details(details,isEnable) {
            reportCarerViewModel.onDetailsChange(it)
        }
    }
}


@Composable
fun FoodTable(isEnable: Boolean, foodReport: List<Food>, dialog: () -> Unit) {
    Column {
        RowAction("Comida", "Status", GeneralColor, isEnable, action = dialog) {
            Icon(Icons.Filled.Add, contentDescription = "Add element ")
        }
        if (foodReport.isEmpty()) {
            Text(
                text = "No has agregado ningun registro",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        } else {
            foodReport.forEachIndexed { index: Int, food: Food ->
                RowAction(
                    food = food.food,
                    description = food.status,
                    color = Color.Gray,
                    isEnable = isEnable,
                    action = {
                        Log.i("Item rowAction", "$index")
                    }) {
                    Icon(Icons.Filled.NoteAdd, "Edit food report")
                }
            }
        }
    }
}

@Composable
fun EvacuationTable(isEnable: Boolean, evacuationReport: List<Evacuation>, dialog: () -> Unit) {

    Column {
        RowAction("Evacuación", "Hora", GeneralColor, isEnable, action = dialog) {
            Icon(Icons.Filled.Add, contentDescription = "Add element ")
        }
        if (evacuationReport.isEmpty()) {

            Text(
                text = "No has agregado ningun registro",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        } else {

            evacuationReport.forEachIndexed { index: Int, evacuation: Evacuation ->
                val hr = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(evacuation.date)
                RowAction(
                    food = evacuation.evacuation,
                    description = hr,
                    color = Color.Gray,
                    isEnable = isEnable,
                    action = {
                        Log.i("Evac item rowAction", "$index")
                    }) {
                    Icon(Icons.Filled.NoteAdd, "Edit evacuation report")
                }
            }
        }
    }
}

@Composable
fun Details(details: String, isEnable: Boolean,onTextChange: (String) -> Unit) {
    TextField(
        readOnly = !isEnable,
        modifier = Modifier.defaultMinSize(361.dp, 239.dp),
        value = details,
        onValueChange = onTextChange,
        label = {
            Text("Obeservaciones")
        })
}