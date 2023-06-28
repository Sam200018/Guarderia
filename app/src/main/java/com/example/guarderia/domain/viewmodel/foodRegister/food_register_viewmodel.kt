package com.example.guarderia.domain.viewmodel.foodRegister

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guarderia.GuarderiaApplication
import com.example.guarderia.data.AuthRepository
import com.example.guarderia.data.IngestionsRepository
import com.example.guarderia.model.IngestionsRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
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
        getIngestions(type)
    }

    private fun getIngestions(type: String) {
        viewModelScope.launch {
            val tokenEntity = authRepository.getToken()
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val ingestionsRequest = IngestionsRequest(type, date )
            val ingestionsResponse =
                ingestionsRepository.getIngestionsByGroup(tokenEntity!!.token, ingestionsRequest)
            Log.i("Ingestions",ingestionsResponse.toString())
            if(ingestionsResponse.food!=null && ingestionsResponse.ingestions!=null){
                uiState.value= FoodRegisterUiState(IngestionStatus.Success, food = ingestionsResponse.food,ingestions = ingestionsResponse.ingestions)
            }else{
                uiState.value= FoodRegisterUiState(IngestionStatus.Undefined, food = null,ingestions = null)
            }
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