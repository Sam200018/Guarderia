package com.example.guarderia.domain.viewmodel.home

import com.example.guarderia.model.Announcement

sealed interface HomeUiState {

    data class Success(val announcements: List<Announcement>, val type: Int) : HomeUiState

    object Undefined : HomeUiState

    object Loading : HomeUiState

    object Error : HomeUiState


}