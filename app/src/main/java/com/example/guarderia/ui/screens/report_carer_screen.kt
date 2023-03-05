package com.example.guarderia.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.ui.theme.GeneralColor
import com.example.guarderia.ui.utils.RowAction
import com.example.guarderia.ui.utils.SelectedDate
import com.example.guarderia.ui.utils.Separator

@Composable
fun ReportCarerScreen(reportCarerViewModel: ReportCarerViewModel,navigator:NavHostController) {

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
    val openDialog = remember {
        mutableStateOf(false)
    }

    val selectedChild = reportCarerViewModel.child
    val father= reportCarerViewModel.father


    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        CustomDialog(openDialog = openDialog)
        Text(selectedChild!!.name, fontSize = 24.sp)
        Spacer(Modifier.size(10.dp))
        Text("Edad: ${selectedChild.age} años", fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        Text(father!!.name, fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        Text(father.phone, fontSize = 20.sp)
        Spacer(Modifier.size(10.dp))
        SelectedDate(context)
        Spacer(Modifier.size(15.dp))
        Separator("Reporte De Comida Ingerida")
        FoodTable(dialog = {
            openDialog.value = true
        })
        Separator("Reporte De Evacuaciones")
        EvacuationTable()
        Separator("Observaciones")
        Spacer(Modifier.size(10.dp))
        Details()
    }
}


@Composable
fun FoodTable(dialog: () -> Unit) {
    val listState = rememberLazyListState(0)


    LazyColumn(state = listState) {

        item {
            RowAction("Comida", "Status", GeneralColor, action = dialog) {
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