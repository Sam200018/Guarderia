package com.example.guarderia.domain.entities

import java.util.*

data class Report(
    val isEditable: Boolean,
    val foodRecord: ArrayList<Food>,
    val evacuationRecord: ArrayList<Evacuation>,
    var detailsRecord: String,
)
data class Evacuation(var evacuation: String, var date: Date)

data class Food(var food: String, var status: String)

