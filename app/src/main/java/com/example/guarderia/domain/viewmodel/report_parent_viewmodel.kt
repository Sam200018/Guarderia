package com.example.guarderia.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.Child
import com.example.guarderia.domain.entities.Evacuation
import com.example.guarderia.domain.entities.Food
import com.example.guarderia.domain.entities.User
import java.util.*

class ReportParentViewModel(private val navigator: NavHostController) : ViewModel() {

    var child by mutableStateOf<Child?>(null)
    var tutor by mutableStateOf<User?>(null)

    private val _date = MutableLiveData<Date>()
    val date: LiveData<Date> = _date

    private val _foodRecord = mutableStateListOf<Food>()
    val foodRecord: List<Food> = _foodRecord

    private val _evacuationRecord = mutableStateListOf<Evacuation>()
    val evacuationRecord: List<Evacuation> = _evacuationRecord

    private val _detailsRecord = String()
    val detailsRecord: String = _detailsRecord

    fun report(selectedChild: Child){
        child = selectedChild
        tutor = users[child!!.teachermail]
    }

    fun changeDate(selectedDate: Date){
        _date.value = selectedDate
    }

}