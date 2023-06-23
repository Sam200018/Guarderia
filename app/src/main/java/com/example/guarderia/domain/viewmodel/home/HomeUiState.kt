package com.example.guarderia.domain.viewmodel.home

sealed interface HomeUiState{

    data class Success(val announcements:String,val type: Int): HomeUiState

    object Undefined: HomeUiState

    object Loading: HomeUiState

    object Error: HomeUiState


}