package com.composeapp.responsivenewsapp.di

import com.composeapp.responsivenewsapp.data.impl.NewsRepoImpl
import com.composeapp.responsivenewsapp.data.remote.NewsSource
import com.composeapp.responsivenewsapp.domain.repo.NewsRepo
import com.composeapp.responsivenewsapp.domain.usecases.FetchNewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun bindHttpClient():HttpClient{
        return HttpClient(Android){
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            install(ContentNegotiation) {
                json() // or gson() if using Gson
            }


        }

    }


    @Provides
    @Singleton
    fun provideNewsRepo(
        newsSource: NewsSource
    ): NewsRepo = NewsRepoImpl(
        newsSource = newsSource
    )


    @Provides
    @Singleton
    fun provideFetchNewsUseCases(newsRepo: NewsRepo): FetchNewsUseCases {
        return FetchNewsUseCases(newsRepo = newsRepo)
    }


}