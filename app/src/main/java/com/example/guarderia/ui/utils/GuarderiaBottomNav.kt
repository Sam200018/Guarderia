package com.example.guarderia.ui.utils

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guarderia.domain.viewmodel.bottomNav.BottomNavViewModel
import com.example.guarderia.ui.routes.GuarderiaRoutes

//TODO: Add all the titles of the bottom bar
val items = listOf(
    GuarderiaRoutes.Home,
)

@Composable
fun GuarderiaBottomNav(
    bottomNavViewModel: BottomNavViewModel = viewModel(),
    navController: NavController,
    modifer: Modifier = Modifier
) {
    val bottomNavUiState by bottomNavViewModel.uiState.collectAsState()

    BottomNavigation(modifier = modifer) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = bottomNavUiState == index,
                onClick = {
                    bottomNavViewModel.onSelectedRouteChange(index)
                    if (index != bottomNavUiState) {
                        navController.navigate(item.name)
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = null
                    )
                },
                label = { Text(text = stringResource(id = item.title)) }
            )
        }
    }
}