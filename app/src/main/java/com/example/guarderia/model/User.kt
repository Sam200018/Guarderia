package com.example.guarderia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val telephone: String,
    @SerialName("role_id")
    val roleId:Int
)
