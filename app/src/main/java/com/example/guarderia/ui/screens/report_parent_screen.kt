package com.example.guarderia.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.Food
import com.example.guarderia.domain.viewmodel.ReportParentViewModel
import com.example.guarderia.ui.theme.GeneralColor
import com.example.guarderia.ui.utils.Separator
import java.util.*

@Composable
fun ReportParentScreen(reportParentViewModel: ReportParentViewModel, navigator: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                // 1.
                title = {
                    Box(contentAlignment = Alignment.Center){
                        Text("Reporte Diario", fontSize = 24.sp, textAlign = TextAlign.Center)
                    }
                },
                // 2.
                navigationIcon = {
                    IconButton(onClick = {navigator.popBackStack()}){
                        Icon(Icons.Filled.ArrowBack, contentDescription = "BackNavigation")
                    }
                },
                // 3.
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
fun Body(context: Context, reportParentViewModel: ReportParentViewModel){

    val selectedChild = reportParentViewModel.child
    val tutor = reportParentViewModel.tutor

    val date: Date by reportParentViewModel.date.observeAsState(Date())

    val foodRecord = reportParentViewModel.foodRecord
    val evacuationRecord = reportParentViewModel.evacuationRecord
    val detailsRecord = reportParentViewModel.detailsRecord

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

       // selected date

        Separator("Reporte De Comida Ingerida")
        FoodTable(foodRecord)

        Separator("Reporte De Evacuaciones")
        EvacuationTable()

        Separator("Observaciones")
        Spacer(Modifier.size(10.dp))
        DetailsBox()

    }
}

@Composable
fun FoodTable(foodRecord: List<Food>){

    val listState = LazyListState(0)

    LazyColumn(state = listState){



        if(foodRecord.isEmpty()){
            item{
                Text(text = "No se agregó ningún registro", textAlign = TextAlign.Center)
            }
        }
        else{

        }

    }

}

@Composable
fun EvacuationTable(){
    //
}

@Composable
fun DetailsBox(){
    //
}
