package com.example.wiremock_stages_compose.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCaseFlow<P : Any, T>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    protected abstract suspend fun executeOnBackground(params: P? = null): Flow<T>

    suspend fun execute(params: P? = null) = executeOnBackground(params)
        .flowOn(dispatcher)
}