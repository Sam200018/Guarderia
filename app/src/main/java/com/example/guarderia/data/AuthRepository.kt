package com.example.guarderia.data

import com.example.guarderia.model.AuthResponse
import com.example.guarderia.model.LoginRequest
import com.example.guarderia.model.User
import com.example.guarderia.network.AuthDataSourceRemote

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): AuthResponse
    suspend fun logout(token: String)
    suspend fun checkAuthStatus(token: String): AuthResponse
    suspend fun saveToken(token: TokenEntity)
    suspend fun saveUser(user: User)
    suspend fun getUser(): User
    suspend fun getToken(): TokenEntity?
    suspend fun deleteToken()
}


class AuthRepositoryImpl(
    private val authDataSourceRemote: AuthDataSourceRemote,
    private val authDataSourceLocal: AuthDao
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): AuthResponse =
        authDataSourceRemote.login(loginRequest)

    override suspend fun logout(token: String) = authDataSourceRemote.logout(token)

    override suspend fun checkAuthStatus(token: String): AuthResponse =
        authDataSourceRemote.checkStatus(token)

    override suspend fun saveToken(token: TokenEntity) = authDataSourceLocal.saveToken(token)
    override suspend fun saveUser(user: User) = authDataSourceLocal.saveUser(user)

    override suspend fun getUser(): User = authDataSourceLocal.getUser()

    override suspend fun getToken(): TokenEntity? = authDataSourceLocal.getToken()

    override suspend fun deleteToken() = authDataSourceLocal.removeToken()
}