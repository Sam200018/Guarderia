package com.example.guarderia.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guarderia.domain.entities.*
import com.example.guarderia.domain.viewmodel.login.users
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    private val _details = MutableLiveData("")
    val details: LiveData<String> = _details

    fun report(selectedChild: Child) {
        child = selectedChild
        father = users[child!!.tutormail]
        if (child!!.overallReport.isNotEmpty()) {
            _isEditable.value=false
            val auxDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            val report = child!!.overallReport[auxDate]
            _foodReport.addAll(report!!.foodRecord)
            _evacuationReport.addAll(report.evacuationRecord)
            _details.value= report.detailsRecord
        }
    }

    fun changeDate(selectedDate: Date) {
        if (selectedDate.time > Date().time) {
            _date.value = Date()
        } else {
            _date.value = selectedDate
        }
        _foodReport.clear()
        _evacuationReport.clear()
        _details.value = ""
        _isEditable.value = true
//        TODO: buscar todo
    }

    fun addFoodReport(food: Food) {
        _foodReport.add(food)
    }

    fun addFoodEvacuationReport(evacuation: Evacuation) {
        _evacuationReport.add(evacuation)
    }

    fun onDetailsChange(details: String) {
        _details.value = details
    }

    fun saveDayReport() {
        _isEditable.value = false
        val auxDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val newReport = Report(false, ArrayList(foodReport), ArrayList(evacuationReport), details.value!!)
        child!!.overallReport[auxDate] = newReport
    }

    fun back() {
        navigator.popBackStack()
        _foodReport.clear()
        _evacuationReport.clear()
        _details.value = ""
        _isEditable.value = true
    }
}