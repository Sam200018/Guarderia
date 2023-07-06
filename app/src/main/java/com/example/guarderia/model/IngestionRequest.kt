package com.example.guarderia.model

import com.google.gson.annotations.SerializedName

data class IngestionRequest(
    @SerializedName("gratification")
    val gratification: Int,
    @SerializedName("id_child")
    val childId:Int,
    @SerializedName("id_food")
    val foodId:Int
)
