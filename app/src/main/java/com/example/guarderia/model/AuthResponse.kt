package com.example.guarderia.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
    val user: User
)
