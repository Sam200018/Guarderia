package com.example.guarderia.domain.viewmodel.announcement

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

class AnnouncementViewModel(
    private val announcementsRepository: AnnouncementsRepository
) : ViewModel() {

    var titleInput: String by mutableStateOf("")
        private set

    var importanceInput: Int by mutableStateOf(0)
        private set

    var dueDateInput: String by mutableStateOf("")
        private set

    var descriptionInput: String by mutableStateOf("")
        private set

    fun onTitleChange(title: String) {
        titleInput = title
    }

    fun onImportanceChange(importance: Int) {
        importanceInput = importance
    }

    fun onDueDateChange(dueDate: String) {
        dueDateInput = dueDate
    }

    fun onDescriptionChange(description: String) {
        descriptionInput = description
    }


    suspend fun sendAnnouncement() {

            val user = announcementsRepository.getUser()
            val tokenEntity = announcementsRepository.getToken()
//        TODO: get id group
            val announcementToSend = Announcement(
                importance = importanceInput,
                date = "2023-08-03",
                title = titleInput,
                body = descriptionInput,
                idGroup = 1
            )
            announcementsRepository.sendAnnouncement(tokenEntity.token, announcementToSend)

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