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
import androidx.navigation.NavHostController
import com.example.guarderia.domain.viewmodel.ChildrenViewModel
import com.example.guarderia.domain.viewmodel.GroupSelectionViewModel
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.ui.utils.GroupCard

@Composable
fun GroupSelectionScreen(
    groupSelectionViewModel: GroupSelectionViewModel,
    //childrenViewModel: ChildrenViewModel,
    reportCarerViewModel: ReportCarerViewModel,
    userEmail: String,
    type: String,
    //navigator: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Body(modifier = Modifier.align(Alignment.TopStart), groupSelectionViewModel, userEmail, type, reportCarerViewModel)
    }
}

@Composable
fun Body(
    modifier: Modifier,
//    childrenViewModel: ChildrenViewModel,
    groupSelectionViewModel: GroupSelectionViewModel,
    userEmail: String,
    type: String,
    reportCarerViewModel: ReportCarerViewModel
) {

    /*
    //Row() {
    run{
        ExitButtonGS(
            exitClick = { groupSelectionViewModel.exit() },
        )
        Spacer(modifier = Modifier.size(20.dp))
        if (type.equals("teacher")) {
            Text(text = "Bienvenido, Profesor(a)", fontSize = 20.sp)
            displayList(type)
        } else if (type.equals("tutor@")) {
            Text(text = "Bienvenido, tutor", fontSize = 20.sp)
        }
    }

    //}
*/
    //if (userEmail=="brendamateos.prim@gmail.com"){
    LazyColumn {

        item {
            run {
                ExitButtonGS(
                    exitClick = { groupSelectionViewModel.exit() },
                )
                Spacer(modifier = Modifier.size(2.dp))
                if (type.equals("teacher")) {
                    Text(text = "Bienvenido, Profesor(a)", fontSize = 20.sp)
                } else if (type.equals("tutor@")) {
                    Text(text = "Bienvenido, tutor", fontSize = 20.sp)
                }

            }
            this@LazyColumn.item {
                GroupCard(user = userEmail) {
                    groupSelectionViewModel.goToGroup(userEmail, type)
                }
            }
        }
        //}

    }
}

/*
@Composable
fun GroupButton(modifier: Modifier, groupClick: () -> Unit){
    Column(modifier) {

        Button(
            //enabled = isEnable,
            onClick = groupClick,
            modifier = modifier
                .size(width = 250.dp, height = 50.dp)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = GeneralColor
            )

        ) {
            Text(text = "", fontSize = 24.sp)
        }
        if (errorMessage.isNotEmpty()) {
            TextFieldError(errorMessage)
        }

    }

}
*/
@Composable
fun ExitButtonGS(exitClick: () -> Unit) {
    Row() {
        Button(onClick = exitClick) {
            Text(text = "Salir", fontSize = 15.sp)
        }

    }
}
/*
@Composable
fun Header()
*/