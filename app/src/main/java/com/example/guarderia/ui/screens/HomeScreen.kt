package com.example.guarderia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.guarderia.R
import com.example.guarderia.domain.viewmodel.home.HomeUiState
import com.example.guarderia.domain.viewmodel.home.HomeViewModel
import com.example.guarderia.model.Announcement
import com.example.guarderia.ui.theme.EmptyScreenLabelColor
import com.example.guarderia.ui.theme.FilterBarColor
import com.example.guarderia.ui.utils.AnnouncementTab
import com.example.guarderia.ui.utils.FilterButton
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {


    val homeUiState = homeViewModel.uiState
    when (homeUiState) {
        is HomeUiState.Loading -> CheckingScreen()
        is HomeUiState.Success -> SuccessPage(
            modifier = modifier,
            announcements = homeUiState.announcements,
            homeUiState.type,
            homeViewModel,
            navController
        )

        is HomeUiState.Undefined -> Text(text = "No tienes grupo registrado")
        is HomeUiState.Error -> Text(text = "Error, lo siento")
    }

}

@Composable
fun SuccessPage(
    modifier: Modifier,
    announcements: List<Announcement>,
    roleId: Int,
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {

    var refreshing by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            homeViewModel.checkingRole()
            refreshing = false
        }
    }
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshing), onRefresh = {
            refreshing = true
    }) {


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


            if (announcements.isEmpty()){
                LazyColumn(modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center){
                    item {
                        Text(
                            text = stringResource(id = R.string.there_is_not_announcements),
                            color = EmptyScreenLabelColor,
                            fontSize = 24.sp
                        )
                    }
                }
            }else{

            LazyColumn(
                modifier
                    .fillMaxSize()
            ) {
                items(announcements.size) {
                    AnnouncementTab(modifier = modifier, announcement = announcements[it],navController)
                    Box(modifier.height(10.dp))
                }
            }
            }
        }
    }


}


@Composable
fun Spacer(d: Int) {
    Box(Modifier.width(d.dp))
}
