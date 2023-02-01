package com.example.wiremock_stages_compose.domain.mapper

import com.example.wiremock_stages_compose.data.remote.model.RemotePullRequest
import com.example.wiremock_stages_compose.domain.model.PullRequest

fun PullRequest.toRemote() = RemotePullRequest(
    paymentCode = paymentCode
)