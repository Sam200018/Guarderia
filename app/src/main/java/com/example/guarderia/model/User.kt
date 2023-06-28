package com.example.guarderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val telephone: String,
    @SerializedName("role_id")
    val roleId:Int
)
