package com.example.guarderia.model

import kotlinx.serialization.Serializable

@Serializable
data class Food(
  val id:Int,
  val name:String,
  val hour: String,
  val date:String
)
