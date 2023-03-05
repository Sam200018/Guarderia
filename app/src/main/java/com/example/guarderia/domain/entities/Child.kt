package com.example.guarderia.domain.entities

import java.util.*
import kotlin.collections.HashMap

data class Child(
    val name: String,
    val tutormail: String,
    val teachermail: String,
    val age: Int,
    var overallReport: HashMap<Date, Report>,
)

class Evacuation(var evacution: String, var date: Date)

class Food(var food: String, var status: String)
class Report(
    val foodRecord: ArrayList<Food>,
    val evacuationRecord: ArrayList<Evacuation>,
    var detailsRecord: String,
)




