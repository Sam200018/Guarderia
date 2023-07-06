package com.example.guarderia.domain.viewmodel.announce

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class AnnounceViewModel(
    id: String,
    private val announcementsRepository: AnnouncementsRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnnounceUiState(announcement = null))
    val uiState: StateFlow<AnnounceUiState> = _uiState.asStateFlow()

    init {
        getAnnouncementById(id)
    }

    private fun getAnnouncementById(id: String) {
        viewModelScope.launch {
            try {
                val tokenEntity = authRepository.getToken()
                val response = announcementsRepository.getNoticeById(tokenEntity!!.token, id)

                Log.i("responseAnnounce", response.toString())
                _uiState.value = AnnounceUiState(
                    announcement = response.notice,
                    announceStatus = AnnounceStatus.Success
                )

            }catch(e:Exception) {
                _uiState.value = AnnounceUiState(
                    announcement = null,
                    announceStatus = AnnounceStatus.Success,
                    errorMessage = e.message!!
                )
            }
        }
    }

    companion object {
        fun createFactory(type: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GuarderiaApplication)
                val announcementsRepository = application.container.announcementsRepository
                val authRepository = application.container.authRepository

                AnnounceViewModel(type, announcementsRepository, authRepository)
            }
        }
    }
}