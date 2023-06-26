package com.example.guarderia.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Announcement (
    val id: Int= 1,

    @SerializedName("importance") val importance: Int,
    @SerializedName("date") val date: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
)

