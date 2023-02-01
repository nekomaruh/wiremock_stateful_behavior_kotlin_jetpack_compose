package com.example.wiremock_stages_compose.domain.di

import org.koin.dsl.module

val koinModules = module {
    includes(networkModule, useCaseModule, viewModelModule)
}