package com.example.guarderia.data

import com.example.guarderia.model.User
import com.example.guarderia.network.AnnouncementsDataSourceRemote

interface AnnouncementsRepository {
    suspend fun getToken(): TokenEntity

    suspend fun getUser(): User

    suspend fun getAllAnnouncementsById(token: String)
}

class AnnouncementsRepositoryImpl(
    private val authDataSourceLocal: AuthDao,
    private val announcementsDataSourceRemote: AnnouncementsDataSourceRemote
) : AnnouncementsRepository {
    override suspend fun getToken(): TokenEntity = authDataSourceLocal.getToken()!!

    override suspend fun getUser(): User = authDataSourceLocal.getUser()

    override suspend fun getAllAnnouncementsById(token: String) =
        announcementsDataSourceRemote.getNoticeById(token)

}