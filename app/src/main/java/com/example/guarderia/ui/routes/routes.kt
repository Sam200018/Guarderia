package com.example.guarderia.ui.routes

import androidx.annotation.StringRes
import com.example.guarderia.R

sealed class Routes(
    val route: String,
    @StringRes val title: Int,
    val drawableRes: Int = R.drawable.annoucements_icon
) {
    object Login : Routes("login", R.string.login)
    object Announcement : Routes("announcement", R.string.announcements)
    object Home : Routes("home", R.string.announcements, R.drawable.annoucements_icon)
    object Checking : Routes("checking", R.string.checking)
    object AddNotice : Routes("addNotice", R.string.addNotice)
    object ViewNotice : Routes("notice", R.string.notice)
    object Notes : Routes("notes", R.string.notes, R.drawable.notes_icon)
//    object ReportParentScreen : Routes("reportParent")
//    object GroupSelectionScreen : Routes("groupSelection")

    companion object{
        fun fromValue(value: String):Routes{
            return when(value){
                "login"->Login
                "home"->Home
                "announcement"->Announcement
                "checking"->Checking
                "addNotice"->AddNotice
                "notice"->ViewNotice
                "notes"->Notes
                else -> Home
            }
        }
    }
}

