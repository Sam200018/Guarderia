package com.example.guarderia.network

import com.example.guarderia.model.IngestionRequest
import com.example.guarderia.model.IngestionsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface IngestionsDataSourceRemote {

    @GET("getIngestasByGroup/{type}/{date}")
    suspend fun getIngestionsByGroup(
        @Header("Authorization") token: String,
        @Path("type") type: String,
        @Path("date") date: String,
    ): IngestionsResponse

    @POST("createIngestion")
    suspend fun createIngestion(
        @Header("Authorization") token: String,
        @Body ingestionRequest: IngestionRequest
    )

    @PUT("editIngesta/{id_ingesta}")
    suspend fun editIngestion(
        @Header("Authorization") token: String,
        @Path("id_ingesta") ingestionId: String,
        @Body ingestionRequest: IngestionRequest
    )
}