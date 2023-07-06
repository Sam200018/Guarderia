package com.example.guarderia.network

import com.example.guarderia.model.LoginRequest
import com.example.guarderia.model.AuthResponse
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthDataSourceRemote {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest):AuthResponse

    @POST("logout")
    suspend fun logout(@Header("Authorization")token: String)

    @GET("check-status")
    suspend fun checkStatus(@Header("Authorization")token:String): AuthResponse
}