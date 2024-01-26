package com.priyo.pigeon.core.di

import com.priyo.pigeon.core.events.AnalyticsLoggerImpl
import com.priyo.core.domain.events.IAnalyticsLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    @Singleton
    fun providesAnalyticsLogger(): IAnalyticsLogger {
        return AnalyticsLoggerImpl()
    }
}