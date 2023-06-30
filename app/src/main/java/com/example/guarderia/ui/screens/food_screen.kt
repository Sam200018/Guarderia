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
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun FoodScreen(modifier: Modifier = Modifier, navController: NavController) {
    val calendar = Calendar.getInstance()
    val date = Date()
    calendar.time = date

    val dayOfWeek = calendar.get(Calendar.DAY_OF_MONTH)
    val monthOfYear = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)
    val localDate = LocalDate.of(year, monthOfYear+1, dayOfWeek)
    val day = localDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"))
    val dayOfMonth = localDate.dayOfMonth
    val month = localDate.month.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"))
    Column(
        modifier
            .fillMaxWidth()
            .height(700.dp)
            .padding(vertical = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        FoodDate(day, dayOfMonth, month)
        FoodButton(
            modifier = modifier,
            label = R.string.breakfast,
            color = R.color.breakfastColor
        ) {
            navController.navigate(Routes.BreakfastRegister.route + "/desayuno")
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
fun FoodDate(dayOfWeek: String, dayOfMonth: Int, month: String) {

    Text(text = "$dayOfWeek $dayOfMonth de $month ", fontSize = 28.sp)
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