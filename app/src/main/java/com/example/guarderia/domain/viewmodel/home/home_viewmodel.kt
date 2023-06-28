package com.example.guarderia.domain.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guarderia.GuarderiaApplication
import com.example.guarderia.data.AnnouncementsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val announcementsRepository: AnnouncementsRepository
) : ViewModel() {

    var uiState = MutableStateFlow(HomeUiState(announcements = null))
        private set


    init {
        viewModelScope.launch {
            checkingRole()
        }
    }

    suspend fun checkingRole() {
        try {
            val tokenEntity = announcementsRepository.getToken()
            if (tokenEntity != null) {
                val user = announcementsRepository.getUser()
                val announcementResponse =
                    announcementsRepository.getAllAnnouncementsById(tokenEntity.token)
                uiState.value = HomeUiState(uiStatus = UiStatus.Success, type = user.roleId, announcements = announcementResponse.notices)
            }

        }catch (e:Exception){
            uiState.value = HomeUiState(
                uiStatus = UiStatus.Error, announcements = null
            )
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