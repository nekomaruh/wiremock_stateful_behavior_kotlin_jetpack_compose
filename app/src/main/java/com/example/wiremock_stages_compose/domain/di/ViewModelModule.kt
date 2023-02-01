package com.example.wiremock_stages_compose.domain.di

import com.example.wiremock_stages_compose.ui.viewmodel.StageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        StageViewModel(get(), get())
    }
}