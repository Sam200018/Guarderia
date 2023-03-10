package com.example.guarderia.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.Child
import com.example.guarderia.ui.routes.Routes

val registeredChildren: HashMap<Int, Child> = hashMapOf(
    1 to Child("Joel Miller","samuelbaubas@gmail.com","brendamateos.prim@gmail.com",2, hashMapOf()),
    2 to Child("Kratos Hernandez","hpelayoc@gmail.com","brendamateos.prim@gmail.com",3, hashMapOf()),
    3 to Child("Serre Siete","samuelbaubas@gmail.com","pruebaprofe@mail.com",4, hashMapOf()),
    4 to Child("Bar Simson","hpelayoc@gmail.com","pruebaprofe@mail.com",3, hashMapOf()),
    5 to Child("Barry Allen","pruebatutor@mail.com","pruebatutor.prim@gmail.com",2, hashMapOf()),
    6 to Child("Bruce Wayne","pruebatutor@mail.com","brendamateos.prim@gmail.com",5, hashMapOf()),
    7 to Child("Lisa Simson","hpelayoc@gmail.com","pruebaprofe@mail.com",3, hashMapOf()),
    8 to Child("Mario Castañeda","samuelbaubas@gmail.com","brendamateos.prim@gmail.com",3, hashMapOf()),
    9 to Child("Luis Castañeda","samuelbaubas@gmail.com","brendamateos.prim@gmail.com",3, hashMapOf()),
    10 to Child("Ellie Williams","pruebatutor@mail.com","pruebaprofe@mail.com",3, hashMapOf()),
    11 to Child("Julio Garfias","pruebatutor@mail.com","brendamateos.prim@gmail.com",3, hashMapOf())
)

class ChildrenViewModel(private val navigator: NavHostController) : ViewModel(){
    fun exit(){
        navigator.navigate("${Routes.LoginScreen.route}")
    }

    fun report(){
        navigator.navigate(Routes.ReportCarerScreen.route)
    }
    fun viewReport(){
        navigator.navigate(Routes.ReportParentScreen.route)
    }
}