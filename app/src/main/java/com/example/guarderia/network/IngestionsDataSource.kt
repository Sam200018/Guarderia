package com.example.guarderia.network

import com.example.guarderia.model.IngestionsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface IngestionsDataSourceRemote {

    @GET("getIngestasByGroup/{type}/{date}")
    suspend fun getIngestionsByGroup(
        @Header("Authorization") token: String,
        @Path("type") type: String,
        @Path("date") date: String,
    ): IngestionsResponse
}