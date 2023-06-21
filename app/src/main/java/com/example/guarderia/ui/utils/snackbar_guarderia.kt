package com.example.guarderia.ui.utils

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration


suspend fun guarderiaSnackbar(scaffoldState: ScaffoldState, message:String) {
    scaffoldState.snackbarHostState.showSnackbar(message, duration = SnackbarDuration.Short)
    
}