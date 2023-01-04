package com.example.wiremock_stages_compose.data.model

sealed class Result<T> {
    class OnLoading<T> : Result<T>()
    data class OnSuccess<T>(val data: T) : Result<T>()
    data class OnError<T>(val throwable: Throwable) : Result<T>()
}