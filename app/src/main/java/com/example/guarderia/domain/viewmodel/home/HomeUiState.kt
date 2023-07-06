package com.example.guarderia.domain.viewmodel.home

import com.example.guarderia.model.Announcement

enum class UiStatus {
    Loading,
    Undefined,
    Error,
    Success
}

data class HomeUiState(
    val uiStatus: UiStatus = UiStatus.Loading,
    val errorMessage: String = "",
    val type: Int = 0,
    val announcements: List<Announcement>?,
)