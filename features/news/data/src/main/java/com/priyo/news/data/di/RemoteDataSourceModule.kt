package com.priyo.news.data.di

import com.priyo.news.data.source.remotedatasource.INewsRemoteDataSource
import com.priyo.news.data.source.remotedatasource.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindNewsRemoteDataSourceProviders(
        newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl,
    ): INewsRemoteDataSource
}
