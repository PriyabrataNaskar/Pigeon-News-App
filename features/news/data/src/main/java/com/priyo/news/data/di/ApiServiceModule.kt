package com.priyo.news.data.di

import com.priyo.news.data.source.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideNewsApiServices(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}
