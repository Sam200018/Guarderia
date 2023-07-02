package com.example.guarderia.domain.viewmodel.foodRegister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guarderia.GuarderiaApplication
import com.example.guarderia.data.AuthRepository
import com.example.guarderia.data.IngestionsRepository
import com.example.guarderia.model.Ingestion
import com.example.guarderia.model.IngestionRequest
import com.example.guarderia.model.IngestionsRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FoodRegisterViewModel(
    private val type: String,
    private val ingestionsRepository: IngestionsRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState = MutableStateFlow(FoodRegisterUiState(food = null, ingestions = null))
        private set

    init {
        viewModelScope.launch {
            getIngestions(type)
        }
    }

    suspend fun getIngestions(type: String) {
        try {

            val tokenEntity = authRepository.getToken()
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val ingestionsRequest = IngestionsRequest(type, date)
            val ingestionsResponse =
                ingestionsRepository.getIngestionsByGroup(
                    tokenEntity!!.token,
                    ingestionsRequest
                )
            if (ingestionsResponse.food != null && ingestionsResponse.ingestions != null) {
                uiState.value = FoodRegisterUiState(
                    IngestionStatus.Success,
                    food = ingestionsResponse.food,
                    ingestions = ingestionsResponse.ingestions
                )
            } else {
                uiState.value = FoodRegisterUiState(
                    IngestionStatus.Undefined,
                    food = null,
                    ingestions = null
                )
            }
        } catch (e: Exception) {

        }

    }

    suspend fun recordIngestion(
        ingestion: Ingestion,
        gratification: Int,
        childId: Int,
        foodId: Int
    ) {
        try {
            val tokenEntity = authRepository.getToken()
            val ingestionRequest = IngestionRequest(gratification, childId, foodId)
            if (ingestion.idIngestion == "-1") {
                ingestionsRepository.recordIngestion(tokenEntity!!.token, ingestionRequest)
            } else {
                ingestionsRepository.editIngestion(
                    tokenEntity!!.token,
                    ingestion.idIngestion,
                    ingestionRequest
                )
            }
        } catch (e: Exception) {

        }
    }

    companion object {
        fun createFactory(type: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GuarderiaApplication)
                val ingestionsRepository = application.container.ingestionsRepository
                val authRepository = application.container.authRepository

                FoodRegisterViewModel(type, ingestionsRepository, authRepository)
            }
        }
    }
}