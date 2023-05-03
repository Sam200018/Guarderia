package com.example.guarderia.ui.routes

import androidx.annotation.StringRes
import com.example.guarderia.R

sealed class Routes(val route: String) {
    object LoginScreen : Routes("login")
    object ChildrenScreen : Routes("children")
    object ReportCarerScreen : Routes("reportCarer")
    object ReportParentScreen : Routes("reportParent")
    object GroupSelectionScreen : Routes("groupSelection")
}

//*
// Favor de usar esta notacion para crear nuevas rutas dentro de la app
// y solo agregan el titulo dentro de res>values>string.xml para que aqui lo
// lo pueda usar
// *//
enum class GuarderiaRoutes(@StringRes val title: Int) {
    Login(R.string.login),
    Home(R.string.announcements),
    ChildrenScreen(2),
    ReportCarerScreen(3),
    ReportParentScreen(4),
    GroupSelectionScreen(5),

}