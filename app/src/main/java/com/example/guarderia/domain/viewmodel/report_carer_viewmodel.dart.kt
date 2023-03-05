package com.example.guarderia.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.Child
import com.example.guarderia.domain.entities.User

class ReportCarerViewModel(private val navigator:NavHostController):ViewModel(){

    var child  by mutableStateOf<Child?>(null)
    var father by mutableStateOf<User?>(null)

    fun report(selectedChild: Child){
        child = selectedChild;
        father= tutorUsers[child!!.tutormail]
    }

}