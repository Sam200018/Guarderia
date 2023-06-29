package com.example.guarderia.network

import com.example.guarderia.model.Announcement
import com.example.guarderia.model.AnnouncementResponse
import com.example.guarderia.model.AnnouncementsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AnnouncementsDataSourceRemote {

    @GET("allNotices")
    suspend fun getNoticeById(@Header("Authorization") token: String): AnnouncementsResponse

    @POST("createNotice")
    suspend fun createNotice(
        @Header("Authorization") token: String,
        @Body announcement: Announcement
    )


    @GET("getNoticeByID/{id_notice}")
    suspend fun getNotice(
        @Header("Authorization") token: String,
        @Path("id_notice") id: String
    ):AnnouncementResponse
}