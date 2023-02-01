package com.example.wiremock_stages_compose.domain.di

import com.example.wiremock_stages_compose.domain.PullDataUseCase
import com.example.wiremock_stages_compose.domain.ResetScenarioUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        PullDataUseCase(get())
    }
    single {
        ResetScenarioUseCase(get())
    }
}