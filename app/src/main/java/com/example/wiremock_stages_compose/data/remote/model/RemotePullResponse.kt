package com.example.wiremock_stages_compose.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePullResponse (
    @SerializedName("content") val content: String,
    @SerializedName("status") val status: String
)