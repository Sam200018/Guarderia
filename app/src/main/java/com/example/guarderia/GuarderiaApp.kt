package com.example.guarderia

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.guarderia.domain.viewmodel.ChildrenViewModel
import com.example.guarderia.domain.viewmodel.GroupSelectionViewModel
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.domain.viewmodel.ReportParentViewModel
import com.example.guarderia.domain.viewmodel.auth.AuthStatus
import com.example.guarderia.domain.viewmodel.auth.AuthViewModel
import com.example.guarderia.ui.routes.GuarderiaRoutes
import com.example.guarderia.ui.routes.Routes
import com.example.guarderia.ui.screens.CheckingScreen
import com.example.guarderia.ui.screens.ChildrenScreen
import com.example.guarderia.ui.screens.GroupSelectionScreen
import com.example.guarderia.ui.screens.HomeScreen
import com.example.guarderia.ui.screens.LoginScreen
import com.example.guarderia.ui.screens.NotesScreen
import com.example.guarderia.ui.screens.ReportCarerScreen
import com.example.guarderia.ui.screens.ReportParentScreen
import com.example.guarderia.ui.utils.GuarderiaAppBar
import com.example.guarderia.ui.utils.GuarderiaBottomNav
import com.example.guarderia.ui.utils.GuarderiaFloatingActionButton

@Composable
fun GuarderiaApp(modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        GuarderiaRoutes.valueOf(backStackEntry?.destination?.route ?: GuarderiaRoutes.Login.name)

    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory)
    val authStatus by authViewModel.uiState.collectAsState()
    val startDestination = when (authStatus.authStatus) {
        AuthStatus.Authenticated -> GuarderiaRoutes.Home
        AuthStatus.Unauthenticated -> GuarderiaRoutes.Login
        else -> GuarderiaRoutes.Checking
    }


    val reportCarerViewModel = ReportCarerViewModel(navController)
    val reportParentViewModel = ReportParentViewModel(navController)


    Scaffold(
        topBar = {
            if (currentRoute != GuarderiaRoutes.Login) {
                GuarderiaAppBar(
                    currentRoute = currentRoute,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() })
            }
        },
        bottomBar = {
            if (isBottomNavVisible(currentRoute))
                GuarderiaBottomNav(navController = navController)
        },
        floatingActionButton = {
            if (isFloatingActionButtonVisible(currentRoute)) {
                GuarderiaFloatingActionButton()
            }

        }
    ) {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = startDestination.name
        ) {


            composable(GuarderiaRoutes.Login.name) {
                LoginScreen(authViewModel = authViewModel, errorMessage = authStatus.errorMessage)

            }

            composable(GuarderiaRoutes.Checking.name){
                CheckingScreen()
            }

            composable(GuarderiaRoutes.Home.name) {
                HomeScreen()
            }

            composable(GuarderiaRoutes.Notes.name) {
                NotesScreen()
            }

            composable(GuarderiaRoutes.AddNotice.name){

            }




            composable("${Routes.ChildrenScreen.route}/{userEmail}/{type}") {
                val userEmail = it.arguments?.getString("userEmail")
                val type = it.arguments?.getString("type")

                if (userEmail != null && type != null) {
                    ChildrenScreen(
                        ChildrenViewModel(navController),
                        reportCarerViewModel,
                        reportParentViewModel,
                        userEmail,
                        type
                    )
                }
            }

            composable("${Routes.GroupSelectionScreen.route}/{userEmail}/{type}") {
                val userEmail = it.arguments?.getString("userEmail")
                val type = it.arguments?.getString("type")
                if (userEmail != null) {
                    if (type != null) {
                        GroupSelectionScreen(
                            GroupSelectionViewModel(navController),
                            reportCarerViewModel,
                            userEmail,
                            type
                        )
                    }
                }
            }

            composable(Routes.ReportCarerScreen.route) {
                ReportCarerScreen(reportCarerViewModel, navController)
            }

            composable(Routes.ReportParentScreen.route) {
                ReportParentScreen(reportParentViewModel)
            }
        }
    }
}

private fun isBottomNavVisible(currentRoute: GuarderiaRoutes): Boolean {
//    TODO: Add your created route where you do not want to the bottom navigation appears
    return currentRoute != GuarderiaRoutes.Login
}

private fun isFloatingActionButtonVisible(currentRoute: GuarderiaRoutes): Boolean {
//    TODO: Add your created route where you want to the floating action button appears
    return currentRoute == GuarderiaRoutes.Home || currentRoute == GuarderiaRoutes.Notes

}