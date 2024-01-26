package com.priyo.pigeon.core.events

import android.util.Log
import com.priyo.core.domain.events.IAnalyticsLogger
import com.priyo.analytics.controller.AnalyticsTrackerController

/**
 * Keep AnalyticsLoggerImpl in App Module ensures that we can have an interface in Core module.
 * So we don't need to inject analytics module in every feature module.
 */
class AnalyticsLoggerImpl : IAnalyticsLogger {

    override fun trackEvent(
        eventName: String,
        eventProperties: Map<String, Any>,
    ) {
        AnalyticsTrackerController.trackEvent(
            eventName = eventName,
            eventProperties = eventProperties,
        )
        Log.e(
            "tag",
            "==> eventName -> $eventName \n || Properties -> $eventProperties ",
        )
    }
}
