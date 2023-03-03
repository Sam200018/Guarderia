package com.example.guarderia.domain.entities

import java.util.*
import kotlin.collections.HashMap

data class Child(
        val name:String,
        val tutormail:String,
        var teachermail:String,
        var age:Int,
        var overallReport:HashMap<Date,Reporte>?=null,
)

class Evacuacion(var evacuacion: String, var hora: Date)

class Comida(var food: String, var status: String)
class Reporte {
        private val mealRegistry: ArrayList<Comida>
        private val evacRegistry: ArrayList<Evacuacion>
        var campoObservaciones: String? = null

        init {
                mealRegistry = ArrayList()
                evacRegistry = ArrayList()
        }

        fun addMeal(food: String, status: String) {
                val newMeal = Comida(food, status)
                mealRegistry.add(newMeal)
        }

        fun addEvacuacion(evacuacion: String, hora: Date) {
                val newEvac = Evacuacion(evacuacion, hora)
                evacRegistry.add(newEvac)
        }
}

