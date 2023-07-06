package com.example.guarderia.model

import kotlinx.serialization.Serializable

@Serializable
data class IngestionsResponse(
    val food: Food,
    val ingestions: List<Ingestion>
)
