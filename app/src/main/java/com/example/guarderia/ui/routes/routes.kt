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
/**
 * Favor de usar esta notacion para crear nuevas rutas dentro de la app
 * y solo agregan el titulo dentro de res>values>string.xml para que aqui lo
 * lo pueda usar, solo agregar drawable res a las pages que aparecen en el bottom nav bar
 * La puse por defecto para que no se quejen las demas pero les dejo como ejemplo Home para que
 * la sigan de esta manera, los iconos voy a tratar de que esten ya agregados sino favor de pedirlos
 */
enum class GuarderiaRoutes(@StringRes val title: Int,val drawableRes: Int = R.drawable.annoucements_icon) {
    Login(R.string.login),
    Home(R.string.announcements, R.drawable.annoucements_icon),

    Notes(R.string.notes,R.drawable.notes_icon),
    ChildrenScreen(2),
    ReportCarerScreen(3),
    ReportParentScreen(4),
    GroupSelectionScreen(5),

}