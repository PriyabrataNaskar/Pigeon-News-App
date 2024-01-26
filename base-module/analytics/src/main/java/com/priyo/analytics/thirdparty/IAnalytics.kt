package com.priyo.analytics.thirdparty

import android.content.Context

interface IAnalytics {

    fun initAnalytics(
        token: String,
        trackAutomaticEvent: Boolean,
        context: Context,
    )

    fun setUser()

    fun unsetUser()

    fun trackEvent(
        eventName: String,
        eventProperties: Map<String, Any>,
    )

    fun reset()
}
