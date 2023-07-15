package com.priyo.news.data.di

import com.priyo.news.data.repository.NewsRepositoryImpl
import com.priyo.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindIWishListSourceProviders(
        wishListRepositoryImpl: NewsRepositoryImpl,
    ): NewsRepository
}
