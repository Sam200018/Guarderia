package com.example.guarderia.data

import com.example.guarderia.model.AuthResponse
import com.example.guarderia.network.AuthDataSourceRemote

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): AuthResponse
    suspend fun logout()
    suspend fun checkAuthStatus(token: String): AuthResponse
    suspend fun saveToken(token: TokenEntity)
    suspend fun getToken(): TokenEntity?
    suspend fun deleteToken()
}


class AuthRepositoryImpl(
    private val authDataSourceRemote: AuthDataSourceRemote,
    private val authDataSourceLocal: AuthDao
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): AuthResponse =
        authDataSourceRemote.login(loginRequest)

    override suspend fun logout() = authDataSourceRemote.logout()

    override suspend fun checkAuthStatus(token: String): AuthResponse =
        authDataSourceRemote.checkStatus(token)

    override suspend fun saveToken(token: TokenEntity) = authDataSourceLocal.saveToken(token)

    override suspend fun getToken(): TokenEntity? = authDataSourceLocal.getToken()

    override suspend fun deleteToken() = authDataSourceLocal.removeToken()
}