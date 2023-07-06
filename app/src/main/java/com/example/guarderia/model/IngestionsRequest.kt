package com.example.guarderia.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class IngestionsRequest(
    @SerializedName("type") val type: String,
    @SerializedName("date") val date: String,
)
