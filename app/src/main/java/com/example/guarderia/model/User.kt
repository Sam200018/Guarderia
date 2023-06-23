package com.example.guarderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val telephone: String,
    @SerialName("role_id")
    val roleId:Int
)
