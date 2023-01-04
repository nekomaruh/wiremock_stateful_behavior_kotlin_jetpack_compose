package com.example.wiremock_stages_compose.data.remote.mapper

import com.example.wiremock_stages_compose.data.remote.model.RemotePullResponse
import com.example.wiremock_stages_compose.domain.model.PullResponse

fun RemotePullResponse.toDomain() = PullResponse(
    content = content,
    status = status
)