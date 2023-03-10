package com.example.guarderia.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guarderia.ui.routes.Routes

class GroupSelectionViewModel(private val navigator: NavHostController): ViewModel(){
    fun exit(){
        navigator.navigate("${Routes.LoginScreen.route}")
    }
    fun report(){
        navigator.navigate(Routes.ReportCarerScreen.route)
    }
    fun goToGroup(userEmail: String, type: String) {

        navigator.navigate("${Routes.ChildrenScreen.route}/${userEmail}/${type}") {

        /*    popUpTo(Routes.LoginScreen.route) {
                inclusive = true
            }*/
        }
    }
}