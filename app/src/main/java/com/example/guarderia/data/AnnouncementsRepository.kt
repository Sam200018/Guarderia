package com.example.guarderia.data

import com.example.guarderia.model.Announcement
import com.example.guarderia.model.AnnouncementResponse
import com.example.guarderia.model.AnnouncementsResponse
import com.example.guarderia.network.AnnouncementsDataSourceRemote

interface AnnouncementsRepository {

    suspend fun getAllAnnouncementsById(token: String): AnnouncementsResponse

    suspend fun sendAnnouncement(
        token: String, announcement: Announcement
    )

    suspend fun getNoticeById(token: String, id: String): AnnouncementResponse

    suspend fun editNotice(token: String, id: String, announcement: Announcement)

    suspend fun deleteNotice(token: String, id: Int)
}

class AnnouncementsRepositoryImpl(
    private val announcementsDataSourceRemote: AnnouncementsDataSourceRemote
) : AnnouncementsRepository {


    override suspend fun getAllAnnouncementsById(token: String): AnnouncementsResponse =
        announcementsDataSourceRemote.getNoticeById(token)

    override suspend fun sendAnnouncement(
        token: String,
        announcement: Announcement
    ) =
        announcementsDataSourceRemote.createNotice(token, announcement)

    override suspend fun getNoticeById(token: String, id: String): AnnouncementResponse =
        announcementsDataSourceRemote.getNotice(token, id)

    override suspend fun editNotice(token: String, id: String, announcement: Announcement) =
        announcementsDataSourceRemote.editNotice(token, id, announcement)

    override suspend fun deleteNotice(token: String, id: Int) =
        announcementsDataSourceRemote.deleteNotice(token, id)


}