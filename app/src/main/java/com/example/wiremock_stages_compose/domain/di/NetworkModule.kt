package com.example.wiremock_stages_compose.domain.di

import com.example.wiremock_stages_compose.data.remote.api.ApiService
import com.example.wiremock_stages_compose.data.remote.repository.Repository
import com.example.wiremock_stages_compose.data.remote.repository.RepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
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
