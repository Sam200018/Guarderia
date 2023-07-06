package com.example.guarderia.data

import com.example.guarderia.model.IngestionRequest
import com.example.guarderia.model.IngestionsRequest
import com.example.guarderia.model.IngestionsResponse
import com.example.guarderia.network.IngestionsDataSourceRemote

interface IngestionsRepository {
    suspend fun getIngestionsByGroup(
        token: String,
        ingestionsRequest: IngestionsRequest
    ): IngestionsResponse

    suspend fun recordIngestion(token: String, ingestionRequest: IngestionRequest)

    suspend fun editIngestion(
        token: String,
        ingestionId: String,
        ingestionRequest: IngestionRequest
    )
}

class IngestionsRepositoryImpl(
    private val ingestionsDataSourceRemote: IngestionsDataSourceRemote
) : IngestionsRepository {

    override suspend fun getIngestionsByGroup(token: String, ingestionsRequest: IngestionsRequest) =
        ingestionsDataSourceRemote.getIngestionsByGroup(
            token,
            ingestionsRequest.type,
            ingestionsRequest.date
        )

    override suspend fun recordIngestion(token: String, ingestionRequest: IngestionRequest) =
        ingestionsDataSourceRemote.createIngestion(token, ingestionRequest)

    override suspend fun editIngestion(
        token: String,
        ingestionId: String,
        ingestionRequest: IngestionRequest
    ) = ingestionsDataSourceRemote.editIngestion(token, ingestionId, ingestionRequest)
}