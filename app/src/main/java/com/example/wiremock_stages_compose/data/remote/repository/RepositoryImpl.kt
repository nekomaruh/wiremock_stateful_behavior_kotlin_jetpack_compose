package com.example.wiremock_stages_compose.data.remote.repository

import com.example.wiremock_stages_compose.data.remote.api.ApiService
import com.example.wiremock_stages_compose.data.remote.model.RemotePullRequest
import com.example.wiremock_stages_compose.data.remote.model.RemotePullResponse

class RepositoryImpl(private val apiService: ApiService) : Repository {

    override suspend fun pull(request: RemotePullRequest): RemotePullResponse {
        return apiService.pull(request.paymentCode)
    }

    override suspend fun reset(): Boolean = apiService.reset().isSuccessful

}