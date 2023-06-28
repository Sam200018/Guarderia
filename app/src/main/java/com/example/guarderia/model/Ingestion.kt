package com.example.guarderia.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Ingestion(
    @SerializedName("id_ingestion")
    val idIngestion:Int,
    val name:String,
    @SerializedName("first_surname")
    val firstSurname: String,
    @SerializedName("second_surname")
    val secondSurname: String,
    val gratification:Int
)
