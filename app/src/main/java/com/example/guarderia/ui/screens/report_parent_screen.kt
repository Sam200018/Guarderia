package com.example.guarderia.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.Evacuation
import com.example.guarderia.domain.entities.Food
import com.example.guarderia.domain.viewmodel.ReportParentViewModel
import com.example.guarderia.ui.theme.GeneralColor
import com.example.guarderia.ui.utils.RowAction
import com.example.guarderia.ui.utils.SelectedDate
import com.example.guarderia.ui.utils.Separator
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReportParentScreen(reportParentViewModel: ReportParentViewModel, navigator: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(contentAlignment = Alignment.Center){
                        Text("Reporte Diario", fontSize = 24.sp, textAlign = TextAlign.Center)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {navigator.popBackStack()}){
                        Icon(Icons.Filled.ArrowBack, contentDescription = "BackNavigation")
                    }
                },
                backgroundColor = GeneralColor
            )
        }
    ){
        Box(modifier = Modifier.fillMaxSize().padding(it)){
            val context = LocalContext.current
            Body(context, reportParentViewModel) // parámetros a función
        }
    }
}

@Composable
fun Body(context: Context, reportParentViewModel: ReportParentViewModel)
{
    val foodRecord = reportParentViewModel.foodRecord
    val evacuationRecord = reportParentViewModel.evacuationRecord
    val detailsRecord = reportParentViewModel.detailsRecord

    val selectedChild = reportParentViewModel.child
    val tutor = reportParentViewModel.tutor
    val date: Date by reportParentViewModel.date.observeAsState(Date())

    Column(
        modifier = Modifier.padding(20.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = selectedChild!!.name, fontSize = 24.sp)
        Spacer(modifier = Modifier.size(10.dp))

        Text("Edad: ${selectedChild.age} años", fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))

        Text(tutor!!.name, fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))

        Text(tutor.phone, fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))

        // SelectedDate(context, reportCarerViewModel, date)
        Spacer(Modifier.size(15.dp))

        Separator("Reporte De Comida Ingerida")
        StaticFoodTable(isEnable = false, foodRecord, dialog = {})

        Separator("Reporte De Evacuaciones")
        StaticEvacuationTable(isEnable = false, evacuationRecord, dialog = {})

        Separator("Observaciones")
        Spacer(Modifier.size(10.dp))
        StaticDetails(isEnable = false, detailsRecord, onTextChange = {})

    }
}

@Composable
fun StaticFoodTable(isEnable: Boolean, foodReport: List<Food>, dialog: () -> Unit) {
    Column {
        RowAction(
            "Comida",
            "Status",
            GeneralColor,
            isEnable,
            action = dialog,
            iconAction = {}
        )
        if (foodReport.isEmpty()) {
            Text(
                text = "No se agregó ningún registro",
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
                    action = {},
                    iconAction = {}
                )
            }
        }
    }
}

@Composable
fun StaticEvacuationTable(isEnable: Boolean, evacuationReport: List<Evacuation>, dialog: () -> Unit) {
    Column {
        RowAction(
            "Evacuación",
            "Hora",
            GeneralColor,
            isEnable,
            action = dialog,
            iconAction = {}
        )
        if (evacuationReport.isEmpty()) {
            Text(
                text = "No se agregó ningún registro",
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
                    action = {},
                    iconAction = {}
                )
            }
        }
    }
}

@Composable
fun StaticDetails(isEnable: Boolean, details: String, onTextChange: (String) -> Unit) {
    TextField(
        readOnly = !isEnable,
        modifier = Modifier.defaultMinSize(361.dp, 239.dp),
        value = details,
        onValueChange = onTextChange,
        label = {
            Text("Observaciones")
        }
    )
}