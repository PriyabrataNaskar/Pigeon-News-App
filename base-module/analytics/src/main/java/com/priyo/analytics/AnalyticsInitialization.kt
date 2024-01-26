package com.priyo.analytics

import android.content.Context
import com.priyo.analytics.controller.AnalyticsTrackerController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnalyticsInitialization constructor(
    private val coroutineScope: CoroutineScope,
    private val context: Context,
) {

    fun init() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                initializeOnBackgroundThread()
            }
        }
    }

    private fun initializeOnBackgroundThread() {
        AnalyticsTrackerController.clearInstances()
        AnalyticsTrackerController.initialize(
            applicationContext = context.applicationContext,
        )
        AnalyticsTrackerController.initializeSdk()
    }
}
