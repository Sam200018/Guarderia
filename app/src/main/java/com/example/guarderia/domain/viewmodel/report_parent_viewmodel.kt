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
import java.text.SimpleDateFormat
import java.util.*

class ReportParentViewModel(private val navigator: NavHostController) : ViewModel() {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    var child by mutableStateOf<Child?>(null)
    var tutor by mutableStateOf<User?>(null)

    private val _date = MutableLiveData<Date>()
    val date: LiveData<Date> = _date

    private val _foodRecord = mutableStateListOf<Food>()
    val foodRecord: List<Food> = _foodRecord

    private val _evacuationRecord = mutableStateListOf<Evacuation>()
    val evacuationRecord: List<Evacuation> = _evacuationRecord

    val detailsRecord = mutableStateOf<String>("")

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    fun viewReport(selectedChild: Child){
        child = selectedChild
        tutor = users[child!!.teachermail]
        val auxDate= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val reportDay= child!!.overallReport[auxDate]
        if(reportDay!=null){
            _foodRecord.addAll(reportDay.foodRecord)
            _evacuationRecord.addAll(reportDay.evacuationRecord)
            detailsRecord.value = reportDay.detailsRecord
        }
    }

    fun back():Unit{
        navigator.popBackStack()
        _foodRecord.clear()
        _evacuationRecord.clear()
        detailsRecord.value = ""
    }

    fun changeDate(selectedDate: Date) {
        _date.value = selectedDate
        _foodRecord.clear()
        _evacuationRecord.clear()
        detailsRecord.value = ""
    }

}