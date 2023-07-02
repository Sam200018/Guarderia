package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guarderia.R
import com.example.guarderia.domain.viewmodel.foodRegister.FoodRegisterViewModel
import com.example.guarderia.domain.viewmodel.foodRegister.IngestionStatus
import com.example.guarderia.model.Food
import com.example.guarderia.model.Ingestion
import com.example.guarderia.ui.utils.CustomDialog
import com.example.guarderia.ui.utils.IngestionTab

@Composable
fun FoodRegisterScreen(
    modifier: Modifier = Modifier,
    foodRegisterViewModel: FoodRegisterViewModel,
    type:String
) {
    val uiState by foodRegisterViewModel.uiState.collectAsState()
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState.ingestionStatus) {
            IngestionStatus.Success -> FoodSuccess(
                modifier,
                uiState.food!!,
                uiState.ingestions!!,
                foodRegisterViewModel,
                type = type
            )

            IngestionStatus.Error -> CheckingScreen()
            IngestionStatus.Undefined -> Text(text = "Lo siento, parece que no hay comida registrada para hoy")
            else -> {
                CheckingScreen()
            }
        }

    }
}

@Composable
fun FoodSuccess(
    modifier: Modifier,
    food: Food,
    ingestions: List<Ingestion>,
    foodRegisterViewModel: FoodRegisterViewModel,
    type: String
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    Text(text = food.name, fontSize = 32.sp, fontWeight = FontWeight(600))
    Text(
        text = food.hour,
        modifier = modifier.shadow(
            elevation = 3.dp,
            ambientColor = colorResource(id = R.color.green_button_disable)
        )
    )
    LazyColumn(
        modifier
            .fillMaxSize()
    ) {
        items(ingestions.size) {
            CustomDialog(
                openDialog = showDialog,
                ingestion = ingestions[it],
                food=food,
                foodRegisterViewModel = foodRegisterViewModel,
                type = type
            )
            IngestionTab(ingestion = ingestions[it]) {
                showDialog.value = true
            }
            Box(modifier.height(5.dp))
        }
    }
}