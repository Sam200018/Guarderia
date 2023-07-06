package com.example.guarderia.domain.viewmodel.foodRegister

import com.example.guarderia.model.Food
import com.example.guarderia.model.Ingestion

enum class IngestionStatus{
    Loading,
    Success,
    Undefined,
    Error
}

data class FoodRegisterUiState(
    val ingestionStatus:IngestionStatus= IngestionStatus.Loading,
    val food: Food?,
    val ingestions:List<Ingestion>?
)
