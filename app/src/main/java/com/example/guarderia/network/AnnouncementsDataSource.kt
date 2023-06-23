package com.example.guarderia.network

import com.example.guarderia.model.Announcement
import com.example.guarderia.model.AnnouncementResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AnnouncementsDataSourceRemote {

    @GET("allNotices")
    suspend fun getNoticeById(@Header("Authorization")token: String): AnnouncementResponse

    @POST("createNotice")
    suspend fun createNotice(
        @Header("Authorization")token: String,
        @Body announcement: Announcement)

}