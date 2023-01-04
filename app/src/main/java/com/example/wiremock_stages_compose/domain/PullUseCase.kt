package com.example.wiremock_stages_compose.domain

import com.example.wiremock_stages_compose.data.model.Result
import com.example.wiremock_stages_compose.data.remote.mapper.toDomain
import com.example.wiremock_stages_compose.data.remote.repository.Repository
import com.example.wiremock_stages_compose.domain.mapper.toRemote
import com.example.wiremock_stages_compose.domain.model.PullRequest
import com.example.wiremock_stages_compose.domain.model.PullResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

const val DELAY_MILLIS = 1000L
const val RETRIES = 20

class PullUseCase(private val repository: Repository) :
    UseCase<String, Result<PullResponse>>() {

    private var lastStatus = "Unknown"

    override suspend fun executeOnBackground(params: String?): Flow<Result<PullResponse>> {
        check(params != null) { throw IllegalArgumentException() }
        var attempt = 1

        return flow<Result<PullResponse>>  {
            do {
                val pullRequest = PullRequest(params)
                val pullResponse = repository.pull(pullRequest.toRemote())
                val status = pullResponse.status
                if (lastStatus != status) {
                    emit(Result.OnSuccess(pullResponse.toDomain()))
                    lastStatus = status
                }
                delay(DELAY_MILLIS)
            } while (attempt++ < RETRIES)
        }.catch { error ->
            emit(Result.OnError(error))
        }
    }
}