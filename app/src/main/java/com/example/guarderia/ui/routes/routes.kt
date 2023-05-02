package com.example.guarderia.ui.routes

sealed class Routes(val route: String) {
    object LoginScreen : Routes("login")
    object ChildrenScreen : Routes("children")
    object ReportCarerScreen : Routes("reportCarer")
    object ReportParentScreen : Routes("reportParent")
    object GroupSelectionScreen : Routes("groupSelection")
}

//*
// Favor de usar esta notacion para crear nuevas rutas dentro de la app
//
// *//
enum class GuarderiaRoutes() {
    Login,
    Home,
    ChildrenScreen,
    ReportCarerScreen,
    ReportParentScreen,
    GroupSelectionScreen,

}