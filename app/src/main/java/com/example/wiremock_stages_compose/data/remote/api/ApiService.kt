package com.example.wiremock_stages_compose.data.remote.api

import com.example.wiremock_stages_compose.data.remote.model.RemotePullResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("api/pull/{paymentCode}")
    suspend fun pull(@Path("paymentCode") paymentCode: String): RemotePullResponse
}