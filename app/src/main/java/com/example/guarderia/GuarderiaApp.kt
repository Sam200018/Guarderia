package com.example.guarderia

import android.util.Log
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
import com.example.guarderia.domain.viewmodel.auth.AuthStatus
import com.example.guarderia.domain.viewmodel.auth.AuthUiState
import com.example.guarderia.domain.viewmodel.auth.AuthViewModel
import com.example.guarderia.domain.viewmodel.foodRegister.FoodRegisterViewModel
import com.example.guarderia.domain.viewmodel.home.HomeViewModel
import com.example.guarderia.ui.routes.Routes
import com.example.guarderia.ui.screens.AddNotice
import com.example.guarderia.ui.screens.CheckingScreen
import com.example.guarderia.ui.screens.FoodRegisterScreen
import com.example.guarderia.ui.screens.FoodScreen
import com.example.guarderia.ui.screens.HomeScreen
import com.example.guarderia.ui.screens.LoginScreen
import com.example.guarderia.ui.screens.NotesScreen
import com.example.guarderia.ui.screens.ViewNoticeScreen
import com.example.guarderia.ui.utils.GuarderiaAppBar
import com.example.guarderia.ui.utils.GuarderiaBottomNav
import com.example.guarderia.ui.utils.GuarderiaFloatingActionButton

@Composable
fun GuarderiaApp(modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        Routes.fromValue(backStackEntry?.destination?.route ?: "checking")
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory)
    val authStatus by authViewModel.uiState.collectAsState()
    val startDestination = when (authStatus.authStatus) {
        AuthStatus.Authenticated -> Routes.Home
        AuthStatus.Unauthenticated -> Routes.Login
        else -> Routes.Checking
    }


    Scaffold(
        topBar = {
            if (currentRoute.route != Routes.Login.route) {
                GuarderiaAppBar(
                    currentRoute = currentRoute,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    authViewModel = authViewModel,
                    isLogoutEnable = isLogoutVisible(currentRoute)
                )
            }
        },
        bottomBar = {
            if (isBottomNavVisible(currentRoute))
                GuarderiaBottomNav(navController = navController)
        },
        floatingActionButton = {
            if (isFloatingActionButtonVisible(currentRoute, authStatus)) {
                GuarderiaFloatingActionButton(navController, currentRoute.route)
            }

        }
    ) {
        val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = startDestination.route
        ) {


            composable(Routes.Login.route) {
                LoginScreen(authViewModel = authViewModel, errorMessage = authStatus.errorMessage)

            }

            composable(Routes.Checking.route) {
                CheckingScreen()
            }

            composable(Routes.Notes.route) {
                NotesScreen()
            }

            composable(Routes.Home.route) {
                HomeScreen(Modifier, homeViewModel, navController)
            }
            composable(Routes.AddNotice.route) {
                AddNotice(navController = navController, homeViewModel = homeViewModel)
            }
            composable(Routes.ViewNotice.route + "/{id}") { navBackStackEntry ->
                val announcementId = navBackStackEntry.arguments!!.getString("id")
                ViewNoticeScreen(id = announcementId ?: "0")
            }
            composable(Routes.Food.route) {
                FoodScreen(navController = navController)
            }
            composable(Routes.BreakfastRegister.route + "/{type}") { navBackStackEntry ->
                val type = navBackStackEntry.arguments!!.getString("type") ?: ""
                Log.i("type", type)
                val foodRegisterViewModel: FoodRegisterViewModel= viewModel(factory = FoodRegisterViewModel.createFactory(type))
                FoodRegisterScreen(foodRegisterViewModel = foodRegisterViewModel)
            }
            composable(Routes.CollationRegister.route) {
//                FoodRegisterScreen()
            }
            composable(Routes.LunchRegister.route) {
//                FoodRegisterScreen()
            }

        }
    }
}

fun isLogoutVisible(currentRoute: Routes): Boolean {
    return currentRoute.route == Routes.Home.route || currentRoute.route == Routes.Notes.route
}


private fun isBottomNavVisible(currentRoute: Routes): Boolean {
//    TODO: Add your created route where you do not want to the bottom navigation appearsa
    Log.i("Route Nav", currentRoute.route)
    return currentRoute.route != Routes.Login.route && currentRoute.route != Routes.AddNotice.route
}

private fun isFloatingActionButtonVisible(
    currentRoute: Routes,
    authStatus: AuthUiState
): Boolean {
//    TODO: Add your created route where you want to the floating action button appears
    return (currentRoute.route == Routes.Home.route || currentRoute.route == Routes.Notes.route) && authStatus.user?.roleId != 2

}