package com.example.wiremock_stages_compose.ui.mapper

import com.example.wiremock_stages_compose.domain.model.PullResponse
import com.example.wiremock_stages_compose.ui.model.PullData

fun PullResponse.toUi() = PullData(
    content = content,
    status = status
)