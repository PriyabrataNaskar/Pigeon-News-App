package com.priyo.analytics.controller

import android.content.Context
import com.priyo.analytics.thirdparty.MixPanelAnalyticsImpl

object AnalyticsTrackerController : IAnalyticsTrackerController {

    private var applicationContext: Context? = null

    override fun initialize(applicationContext: Context) {
        AnalyticsTrackerController.applicationContext = applicationContext
    }

    override fun initializeSdk() {
        setUpMixPanel()
    }

    private fun setUpMixPanel() {
        applicationContext?.let {
            MixPanelAnalyticsImpl.initAnalytics(
                token = "30c685a83423c62647ecd28cac93ed6f" ?: "",  //todo: move this to local.properties
                trackAutomaticEvent = true,
                context = it,
            )
        }
    }

    override fun setUpUserPropertiesMap(peoplePropertyMap: HashMap<String, Any>) {}

    override fun trackEvent(
        eventName: String,
        eventProperties: Map<String, Any>,
    ) {
        groupingAnalyticsTrackSdks(
            eventName = eventName,
            eventProperties = eventProperties,
        )
    }

    override fun clearInstances() {
        applicationContext = null
        MixPanelAnalyticsImpl.reset()
    }

    private fun groupingAnalyticsTrackSdks(
        eventName: String,
        eventProperties: Map<String, Any>,
    ) {
        MixPanelAnalyticsImpl.trackEvent(
            eventName = eventName,
            eventProperties = eventProperties,
        )
    }
}
