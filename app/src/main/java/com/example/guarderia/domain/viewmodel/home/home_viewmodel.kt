package com.example.guarderia.domain.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guarderia.GuarderiaApplication
import com.example.guarderia.data.AnnouncementsRepository
import com.example.guarderia.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val announcementsRepository: AnnouncementsRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState(announcements = null))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            checkingRole()
        }
    }

    suspend fun checkingRole() {

        try {
            val tokenEntity = authRepository.getToken()
            if (tokenEntity != null) {
                val user = authRepository.getUser()
                val announcementResponse =
                    announcementsRepository.getAllAnnouncementsById(tokenEntity.token)
                _uiState.value = HomeUiState(
                    uiStatus = UiStatus.Success,
                    type = user.roleId,
                    announcements = announcementResponse.notices
                )
            }

        } catch (e: Exception) {
            _uiState.value = HomeUiState(
                uiStatus = UiStatus.Error, announcements = null
            )
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GuarderiaApplication)
                val announcementsRepository = application.container.announcementsRepository
                val authRepository = application.container.authRepository
                HomeViewModel(announcementsRepository, authRepository)
            }
        }
    }

}