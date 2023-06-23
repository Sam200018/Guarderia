package com.example.guarderia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guarderia.R
import com.example.guarderia.domain.viewmodel.home.HomeUiState
import com.example.guarderia.domain.viewmodel.home.HomeViewModel
import com.example.guarderia.ui.theme.EmptyScreenLabelColor
import com.example.guarderia.ui.theme.FilterBarColor
import com.example.guarderia.ui.utils.FilterButton

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    val homeViewModel: HomeViewModel= viewModel(factory = HomeViewModel.Factory)

    val homeUiState = homeViewModel.uiState
    when (homeUiState){
        is HomeUiState.Loading -> CheckingScreen()
        is HomeUiState.Success-> SuccessPage(modifier = modifier, announcements = homeUiState.announcements)
        is HomeUiState.Undefined -> Text(text = "No tienes grupo registrado")
        is HomeUiState.Error -> Text(text = "Error, lo siento")
    }

}

@Composable
fun SuccessPage(modifier: Modifier, announcements:String) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
    ) {

        Row(
            modifier
                .fillMaxWidth()
                .background(color = FilterBarColor)
        ) {
            FilterButton(
                text = stringResource(id = R.string.active_announcements),
                selected = true
            ) {

            }
            Spacer(d = 10)
            FilterButton(
                text = stringResource(id = R.string.expired_announcements),
                selected = false
            ) {

            }

        }


        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(top = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            item {
                Text(
                    text = announcements,
                    color = EmptyScreenLabelColor,
                    fontSize = 24.sp
                )
            }
        }
    }

}


@Composable
fun Spacer(d: Int) {
    Box(Modifier.width(d.dp))
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}