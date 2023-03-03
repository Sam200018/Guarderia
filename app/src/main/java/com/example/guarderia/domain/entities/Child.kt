package com.example.guarderia.domain.entities

import java.util.*
import kotlin.collections.HashMap

data class Child(val name:String, val tutormail:String, var teachermail:String, var age:Int, var mealCalendar:HashMap<Calendar,HashMap<String,String>>?=null, var reporteEvacuaciones:HashMap<Calendar, String>?=null)

