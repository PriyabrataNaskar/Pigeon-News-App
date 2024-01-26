package com.priyo.analytics.controller

import android.content.Context

interface IAnalyticsTrackerController {
    /**
     * Initialize the Analytics SDK with Application context
     *
     * @param applicationContext The current application context
     */
    fun initialize(applicationContext: Context)
    fun initializeSdk()
    fun setUpUserPropertiesMap(peoplePropertyMap: HashMap<String, Any>)
    fun trackEvent(eventName: String, eventProperties: Map<String, Any>)
    fun clearInstances()
}
