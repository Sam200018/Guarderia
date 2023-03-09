package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guarderia.domain.viewmodel.ChildrenViewModel
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.domain.viewmodel.ReportParentViewModel
import com.example.guarderia.domain.viewmodel.registeredChildren
import com.example.guarderia.ui.utils.ChildCard

@Composable
fun ChildrenScreen(
    childrenViewModel: ChildrenViewModel,
    reportCarerViewModel: ReportCarerViewModel,
    reportParentViewModel: ReportParentViewModel,
    userEmail: String,
    type: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Body(modifier = Modifier.align(Alignment.TopStart), childrenViewModel, userEmail, type, reportCarerViewModel, reportParentViewModel)
        //("Selection screen $userEmail, $type")
    }
}

@Composable
fun Body(
    modifier: Modifier,
    childrenViewModel: ChildrenViewModel,
    userEmail: String,
    type: String,
    reportCarerViewModel: ReportCarerViewModel,
    reportParentViewModel: ReportParentViewModel
) {
    val child = registeredChildren[4]
    LazyColumn {
        item {
            ChildCard(child = child!!) {
                if (type == "tutor@") {
                    reportParentViewModel.viewReport(child)
                    childrenViewModel.viewReport()
                } else {
                    reportCarerViewModel.report(child)
                    childrenViewModel.report()

                }
            }
        }
    }
}

fun displayList(type: String) {

}

@Composable
fun ExitButton(exitClick: () -> Unit) {
    Row() {
        Button(onClick = exitClick) {
            Text(text = "Salir", fontSize = 15.sp)
        }

    }
}


/*
@Preview(showBackground = true)
@Composable
fun See(){
    ChildrenScreen(childrenViewModel: ChildrenViewModel, userEmail:String,type:String)
}*/