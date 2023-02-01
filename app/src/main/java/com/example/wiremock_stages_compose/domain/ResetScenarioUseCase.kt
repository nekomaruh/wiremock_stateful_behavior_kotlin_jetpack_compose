package com.example.wiremock_stages_compose.domain

import com.example.wiremock_stages_compose.data.remote.repository.Repository

class ResetScenarioUseCase(
    private val repository: Repository
) : BaseUseCaseRequest<String, Boolean>() {

    override suspend fun executeOnBackground(params: String?): Boolean {
        return repository.reset()
    }

}