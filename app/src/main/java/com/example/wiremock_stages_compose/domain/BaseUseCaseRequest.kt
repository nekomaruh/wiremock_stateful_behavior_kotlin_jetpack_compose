package com.example.wiremock_stages_compose.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.example.wiremock_stages_compose.domain.model.Result

abstract class BaseUseCaseRequest<P, T>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    protected abstract suspend fun executeOnBackground(params: P? = null): T

    suspend fun execute(parameters: P? = null) = flow<Result<T>> {
        runCatching {
            executeOnBackground(parameters)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Error(it))
        }
    }.flowOn(dispatcher)
}