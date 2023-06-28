package com.example.guarderia.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guarderia.R
import com.example.guarderia.ui.routes.Routes

@Composable
fun FoodScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier
            .fillMaxWidth()
            .height(700.dp)
            .padding(vertical = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Date()
//        Box(modifier = modifier.height(51.dp))
        FoodButton(
            modifier = modifier,
            label = R.string.breakfast,
            color = R.color.breakfastColor
        ) {
            navController.navigate(Routes.BreakfastRegister.route)
        }
        FoodButton(
            modifier = modifier,
            label = R.string.collation,
            color = R.color.collationColor
        ) {

            navController.navigate(Routes.CollationRegister.route)
        }
        FoodButton(
            modifier = modifier,
            label = R.string.lunch,
            color = R.color.lunchColor
        ) {

            navController.navigate(Routes.LunchRegister.route)
        }
    }
}

@Composable
fun Date() {
    Text(text = "fecha", fontSize = 28.sp)
}

@Composable
fun FoodButton(modifier: Modifier, @StringRes label: Int, color: Int, foodAction: () -> Unit) {
    Button(
        modifier = modifier
            .height(100.dp)
            .width(284.dp),
        onClick = foodAction,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = color))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = label), fontSize = 40.sp)
            Icon(Icons.Default.ModeEdit, contentDescription = "", modifier = modifier.height(40.dp))
        }
    }
}