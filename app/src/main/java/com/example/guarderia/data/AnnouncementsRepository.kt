package com.example.guarderia.data

import com.example.guarderia.model.Announcement
import com.example.guarderia.model.AnnouncementResponse
import com.example.guarderia.model.User
import com.example.guarderia.network.AnnouncementsDataSourceRemote

interface AnnouncementsRepository {
    suspend fun getToken(): TokenEntity?

    suspend fun getUser(): User

    suspend fun getAllAnnouncementsById(token: String): AnnouncementResponse

    suspend fun getSingleNoticeByID(token: String, id: Int): Announcement

    suspend fun sendAnnouncement(
        token: String, announcement: Announcement
    )

    companion object
}

class AnnouncementsRepositoryImpl(
    private val authDataSourceLocal: AuthDao,
    private val announcementsDataSourceRemote: AnnouncementsDataSourceRemote
) : AnnouncementsRepository {
    override suspend fun getToken(): TokenEntity? = authDataSourceLocal.getToken()

    override suspend fun getUser(): User = authDataSourceLocal.getUser()

    override suspend fun getAllAnnouncementsById(token: String): AnnouncementResponse =
        announcementsDataSourceRemote.getNoticeById(token)

    override suspend fun getSingleNoticeByID(
        token: String,
        id: Int): Announcement =
        announcementsDataSourceRemote.getSingleNoticeByID(token, id)

    override suspend fun sendAnnouncement(
        token: String,
        announcement: Announcement
    ) =
        announcementsDataSourceRemote.createNotice(token, announcement)


}