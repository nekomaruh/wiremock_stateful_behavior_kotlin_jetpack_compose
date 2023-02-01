package com.example.wiremock_stages_compose.domain.model

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val message: Throwable) : Result<T>()
}