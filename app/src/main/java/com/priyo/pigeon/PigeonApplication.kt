package com.priyo.pigeon

import android.app.Application
import com.priyo.analytics.AnalyticsInitialization
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class PigeonApplication : Application() {

    private lateinit var analyticsInitialization: AnalyticsInitialization
    override fun onCreate() {
        super.onCreate()

        analyticsInitialization = AnalyticsInitialization(
            coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            context = applicationContext,
        )
        analyticsInitialization.init()
    }
}
