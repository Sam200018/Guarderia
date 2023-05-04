package com.example.guarderia.domain.viewmodel.bottomNav

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BottomNavViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(0)
    val uiState: StateFlow<Int> = _uiState.asStateFlow()

    fun onSelectedRouteChange(intRoute: Int) {
        _uiState.value = intRoute
    }
}