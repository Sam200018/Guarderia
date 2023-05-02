package com.example.guarderia

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guarderia.domain.viewmodel.ChildrenViewModel
import com.example.guarderia.domain.viewmodel.GroupSelectionViewModel
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.domain.viewmodel.ReportParentViewModel
import com.example.guarderia.ui.routes.GuarderiaRoutes
import com.example.guarderia.ui.routes.Routes
import com.example.guarderia.ui.screens.ChildrenScreen
import com.example.guarderia.ui.screens.GroupSelectionScreen
import com.example.guarderia.ui.screens.HomeScreen
import com.example.guarderia.ui.screens.LoginScreen
import com.example.guarderia.ui.screens.ReportCarerScreen
import com.example.guarderia.ui.screens.ReportParentScreen

@Composable
fun GuarderiaApp(modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme
    val navController = rememberNavController()
    val reportCarerViewModel = ReportCarerViewModel(navController)
    val reportParentViewModel = ReportParentViewModel(navController)

    NavHost(navController = navController, startDestination = GuarderiaRoutes.Login.name) {

        composable(GuarderiaRoutes.Login.name) {
            LoginScreen(navController)
        }

        composable(GuarderiaRoutes.Home.name) {
            HomeScreen()
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