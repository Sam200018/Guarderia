package com.example.guarderia.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.Child

val registeredChildren: HashMap<Int, Child> = hashMapOf(
    1 to Child("Joel Miller","samuelbaubas@gmail.com","brendamateos.prim@gmail.com",2,
    hashMapOf(

    ),
    ),
    2 to Child("Kratos Hernandez","hpelayoc@gmail.com","brendamateos.prim@gmail.com",3),
    3 to Child("Serre Siete","samuelbaubas@gmail.com","pruebaprofe@mail.com",4),
    4 to Child("Bar Simson","hpelayoc@gmail.com","pruebaprofe@mail.com",3),
    5 to Child("Barry Allen","pruebatutor@mail.com","pruebatutor.prim@gmail.com",2),
    6 to Child("Bruce Wayne","pruebatutor@mail.com","brendamateos.prim@gmail.com",5)

)

class ChildrenViewModel(private val navigator: NavHostController) : ViewModel(){

}