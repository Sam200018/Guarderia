package com.example.guarderia.domain.viewmodel.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guarderia.GuarderiaApplication
import com.example.guarderia.data.AnnouncementsRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val announcementsRepository: AnnouncementsRepository
) : ViewModel() {

    var uiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set


    init {
        checkingRole()
    }

    private fun checkingRole() {
        viewModelScope.launch {
            val tokenEntity = announcementsRepository.getToken()
            val user = announcementsRepository.getUser()
//            val announcement = announcementsRepository.getAllAnnouncementsById(tokenEntity.token)
            uiState = HomeUiState.Success("Teacher", user.roleId)
            Log.i("User", user.toString())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GuarderiaApplication)
                val announcementsRepository = application.container.announcementsRepository
                HomeViewModel(announcementsRepository)
            }
        }
    }

}