package com.example.guarderia.ui.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guarderia.domain.viewmodel.ChildrenViewModel

@Composable
fun ChildrenScreen(childrenViewModel: ChildrenViewModel, userEmail:String,type:String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Body(modifier = Modifier.align(Alignment.TopStart), childrenViewModel, userEmail, type )
        //("Selection screen $userEmail, $type")
    }
}

@Composable
fun Body(
    modifier: Modifier,
    childrenViewModel: ChildrenViewModel,
    userEmail: String,
    type: String
){



    Row() {
        ExitButton(
            exitClick = {childrenViewModel.exit()},)
        Spacer(modifier = Modifier.size(20.dp))
        if (type.equals("teacher")){
            Text(text = "Bienvenido, Profesor(a)", fontSize = 20.sp)
            displayList(type)
        }
        else if (type.equals("tutor@")){
            Text(text = "Bienvenido, tutor", fontSize = 20.sp)
        }

    }
}

fun displayList(type:String) {

}

@Composable
fun ExitButton(exitClick: () -> Unit){
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