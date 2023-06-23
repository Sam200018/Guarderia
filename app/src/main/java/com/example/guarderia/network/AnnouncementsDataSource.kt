package com.example.guarderia.network

import retrofit2.http.GET
import retrofit2.http.Header

interface AnnouncementsDataSourceRemote {

    @GET("/AllNotices")
    suspend fun getNoticeById(@Header("Authorization")token: String)

}