package com.example.guarderia.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class ReportCarerViewModel(private  val id:String,private val navigator:NavHostController):ViewModel(){

    private val _name= MutableLiveData<String>()
    val name: LiveData<String>
        get()=_name

    val yearsOld=MutableLiveData<String>()
    val fathersName= MutableLiveData<String>()
    val fathersPhone= MutableLiveData<String>()


     init {

        _name.value="Eliu Eduardo Mendoza"
        yearsOld.value=id
        fathersName.value="Ulises Velez Saldania"
        fathersPhone.value="555555555"
    }

}