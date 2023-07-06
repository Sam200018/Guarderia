package com.example.guarderia.domain.viewmodel.announce

import com.example.guarderia.model.Announcement

enum class AnnounceStatus {
    Loading,
    Success,
    Error
}


data class AnnounceUiState(
    val announceStatus: AnnounceStatus = AnnounceStatus.Loading,
    val errorMessage: String = "",
    val announcement: Announcement?
)
