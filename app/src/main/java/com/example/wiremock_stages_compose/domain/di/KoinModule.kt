package com.example.wiremock_stages_compose.domain.di

import com.example.wiremock_stages_compose.data.remote.repository.RepositoryImpl
import com.example.wiremock_stages_compose.data.remote.api.ApiService
import com.example.wiremock_stages_compose.data.remote.repository.Repository
import com.example.wiremock_stages_compose.domain.PullUseCase
import com.example.wiremock_stages_compose.ui.viewmodel.StageViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single {
        OkHttpClient.Builder().build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("http://10.0.2.2:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
    single<Repository> {
        RepositoryImpl(get())
    }
}

val useCaseModule = module {
    single {
        PullUseCase(get())
    }
}

val viewModelModule = module {
    viewModel {
        StageViewModel(get())
    }
}

val koinModules = module {
    includes(apiModule, useCaseModule, viewModelModule)
}