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





