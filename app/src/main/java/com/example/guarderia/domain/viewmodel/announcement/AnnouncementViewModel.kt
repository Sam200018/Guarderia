package com.example.guarderia.domain.viewmodel.announcement

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guarderia.GuarderiaApplication
import com.example.guarderia.data.AnnouncementsRepository
import com.example.guarderia.model.Announcement
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AnnouncementViewModel(
    private val announcementsRepository: AnnouncementsRepository
) : ViewModel() {

    var uiState = MutableStateFlow(AnnouncementsUiState())
        private set

    var titleInput: String by mutableStateOf("")
        private set

    var importanceInput: Int by mutableStateOf(0)
        private set

    var dueDateInput: Date by mutableStateOf(Date())
        private set

    var descriptionInput: String by mutableStateOf("")
        private set

    fun onTitleChange(title: String) {
        titleInput = title
        uiState.update {currentState->
            currentState.copy(
                isTitleValid = Regex("^[A-Z0-9].*").matches(titleInput)
            )
        }
    }

    fun onImportanceChange(importance: Int) {
        importanceInput = importance
        uiState.update { currentState->
            currentState.copy(
                isImportanceSelected = importanceInput!=0
            )
        }
    }

    fun onDueDateChange(dueDate: Date) {
        dueDateInput = dueDate
    }

    fun onDescriptionChange(description: String) {
        descriptionInput = description
    }


    suspend fun sendAnnouncement() {

        val tokenEntity = announcementsRepository.getToken()
//        TODO: get id group

        val announcementToSend = Announcement(
            importance = importanceInput,
            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(dueDateInput),
            title = titleInput,
            body = descriptionInput,
        )
        try {
            if (tokenEntity != null) {
                announcementsRepository.sendAnnouncement(tokenEntity.token, announcementToSend)
            }

        } catch (e: Exception) {
            Log.e("Send Announcement", e.message ?: "")
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GuarderiaApplication)
                val announcementsRepository = application.container.announcementsRepository

                AnnouncementViewModel(announcementsRepository)
            }
        }
    }

}