package com.example.guarderia.network

import com.example.guarderia.model.Announcement
import com.example.guarderia.model.AnnouncementResponse
import com.example.guarderia.model.AnnouncementsResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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
    ): AnnouncementResponse

    @PUT("editNotice/{id_notice}")
    suspend fun editNotice(
        @Header("Authorization") token: String,
        @Path("id_notice") id: String,
        @Body announcement: Announcement
    )

    @DELETE("destroyNotice/{id_notice}")
    suspend fun deleteNotice(
        @Header("Authorization") token: String,
        @Path("id_notice") id: Int
    )
}