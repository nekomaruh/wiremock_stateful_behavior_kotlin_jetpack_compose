package com.example.wiremock_stages_compose.domain.mapper

import com.example.wiremock_stages_compose.data.remote.model.RemotePullRequest
import com.example.wiremock_stages_compose.domain.model.PullRequest
import com.example.wiremock_stages_compose.domain.model.PullResponse
import com.example.wiremock_stages_compose.ui.model.PullData

fun PullRequest.toRemote() = RemotePullRequest(
    paymentCode = paymentCode
)

fun PullResponse.toUi() = PullData(
    content = content,
    status = status
)