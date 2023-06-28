package com.example.guarderia.data

import com.example.guarderia.model.Announcement
import com.example.guarderia.model.AnnouncementResponse
import com.example.guarderia.network.AnnouncementsDataSourceRemote

interface AnnouncementsRepository {

    suspend fun getAllAnnouncementsById(token: String): AnnouncementResponse

    suspend fun sendAnnouncement(
        token: String, announcement: Announcement
    )
}

class AnnouncementsRepositoryImpl(
    private val announcementsDataSourceRemote: AnnouncementsDataSourceRemote
) : AnnouncementsRepository {



    override suspend fun getAllAnnouncementsById(token: String): AnnouncementResponse =
        announcementsDataSourceRemote.getNoticeById(token)

    override suspend fun sendAnnouncement(
        token: String,
        announcement: Announcement
    ) =
        announcementsDataSourceRemote.createNotice(token, announcement)


}