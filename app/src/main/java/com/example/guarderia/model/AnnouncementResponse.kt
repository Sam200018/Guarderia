package com.example.guarderia.model

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementResponse(
    val notice: Announcement
)
