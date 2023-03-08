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
import java.util.Date

class ReportCarerViewModel(private val navigator: NavHostController) : ViewModel() {

    var child by mutableStateOf<Child?>(null)
    var father by mutableStateOf<User?>(null)

    private val _isEditable = MutableLiveData<Boolean>()
    val isEditable: LiveData<Boolean> = _isEditable

    private val _date = MutableLiveData<Date>()
    val date: LiveData<Date> = _date

    private val _foodReport = mutableStateListOf<Food>()
    val foodReport: List<Food> = _foodReport

    private val _evacuationReport = mutableStateListOf<Evacuation>()
    val evacuationReport: List<Evacuation> = _evacuationReport

    fun report(selectedChild: Child) {
        child = selectedChild;
        father = users[child!!.tutormail]
    }

    fun changeDate(selectedDate: Date) {
        _date.value = selectedDate
    }

    fun addFoodReport(food: Food) {
        _foodReport.add(food);
    }

    fun addFoodEvacuationReport(evacuation: Evacuation) {
        _evacuationReport.add(evacuation)
    }
}