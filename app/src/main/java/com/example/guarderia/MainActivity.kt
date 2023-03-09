package com.example.guarderia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guarderia.domain.viewmodel.ChildrenViewModel
import com.example.guarderia.domain.viewmodel.LoginViewModel
import com.example.guarderia.domain.viewmodel.ReportCarerViewModel
import com.example.guarderia.domain.viewmodel.ReportParentViewModel
import com.example.guarderia.ui.routes.Routes
import com.example.guarderia.ui.screens.ChildrenScreen
import com.example.guarderia.ui.screens.LoginScreen
import com.example.guarderia.ui.screens.ReportCarerScreen
import com.example.guarderia.ui.screens.ReportParentScreen
import com.example.guarderia.ui.theme.GuarderiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuarderiaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navigator = rememberNavController()
                    val reportCarerViewModel= ReportCarerViewModel(navigator)
                    val reportParentViewModel = ReportParentViewModel()

                    NavHost(navController = navigator, startDestination = Routes.LoginScreen.route) {

                        composable(Routes.LoginScreen.route) { LoginScreen(LoginViewModel(navigator)) }

                        composable("${Routes.ChildrenScreen.route}/{userEmail}/{type}") {
                            val userEmail = it.arguments?.getString("userEmail")
                            val type = it.arguments?.getString("type")

                            if (userEmail != null && type != null) {
                                ChildrenScreen(ChildrenViewModel(navigator),reportCarerViewModel, reportParentViewModel, userEmail, type)
                            }
                        }

                        composable(Routes.ReportCarerScreen.route) {
                            ReportCarerScreen(reportCarerViewModel,navigator)
                        }

                        composable(Routes.ReportParentScreen.route){
                            ReportParentScreen(reportParentViewModel, navigator)
                        }

                    }

                }
            }
        }
    }
}


