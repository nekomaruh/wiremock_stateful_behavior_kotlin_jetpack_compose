package com.example.wiremock_stages_compose.data.remote.repository

import com.example.wiremock_stages_compose.data.remote.model.RemotePullRequest
import com.example.wiremock_stages_compose.data.remote.model.RemotePullResponse

interface Repository {
    suspend fun pull(request: RemotePullRequest): RemotePullResponse
    suspend fun reset(): Boolean
}