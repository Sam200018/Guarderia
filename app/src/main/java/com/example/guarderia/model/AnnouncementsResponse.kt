package com.example.guarderia.model

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementsResponse(
    val notices: List<Announcement>
)
