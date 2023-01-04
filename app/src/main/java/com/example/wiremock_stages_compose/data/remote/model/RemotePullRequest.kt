package com.example.wiremock_stages_compose.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePullRequest (
    @SerializedName("paymentCode") val paymentCode: String
)