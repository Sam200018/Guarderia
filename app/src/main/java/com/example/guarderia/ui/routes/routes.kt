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
    object EditNotice: Routes("editNotice",R.string.editAnnouncement)
    object Notes : Routes("notes", R.string.notes, R.drawable.notes_icon)
    object Food : Routes("food", R.string.food, R.drawable.food_icon)
    object BreakfastRegister : Routes("breakfastRegister", R.string.breakfast)
    object CollationRegister : Routes("collationRegister", R.string.collation)
    object LunchRegister : Routes("lunchRegister", R.string.lunch)
//    object ReportParentScreen : Routes("reportParent")
//    object GroupSelectionScreen : Routes("groupSelection")

    companion object {
        fun fromValue(value: String): Routes {
            return when (value) {
                "login" -> Login
                "home" -> Home
                "announcement" -> Announcement
                "checking" -> Checking
                "addNotice" -> AddNotice
                "notice/{id}" -> ViewNotice
                "editNotice/{id}"->EditNotice
                "notes" -> Notes
                "food" -> Food
                "breakfastRegister/{type}" -> BreakfastRegister
                "collationRegister/{type}" -> CollationRegister
                "lunchRegister/{type}" -> LunchRegister
                else -> Home
            }
        }
    }
}

