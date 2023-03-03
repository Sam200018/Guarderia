package com.example.guarderia.ui.routes

sealed class Routes(val route: String) {
    object LoginScreen : Routes("login")
    object ChildrenScreen : Routes("children")
    object ReportCarerScreen : Routes("reportCarer")


}