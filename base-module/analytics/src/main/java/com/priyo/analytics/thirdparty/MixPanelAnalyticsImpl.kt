package com.priyo.analytics.thirdparty

import android.annotation.SuppressLint
import android.content.Context
import com.mixpanel.android.mpmetrics.MixpanelAPI

object MixPanelAnalyticsImpl : IAnalytics {

    @SuppressLint("StaticFieldLeak")
    private var mixPanelApi: MixpanelAPI? = null
    override fun initAnalytics(
        token: String,
        trackAutomaticEvent: Boolean,
        context: Context,
    ) {
        mixPanelApi = MixpanelAPI.getInstance(
            context,
            token,
            trackAutomaticEvent,
        )
    }

    override fun setUser() {}

    override fun unsetUser() {}

    override fun trackEvent(
        eventName: String,
        eventProperties: Map<String, Any>,
    ) {
        mixPanelApi?.trackMap(eventName, eventProperties)
    }

    override fun reset() {
        mixPanelApi?.reset()
    }
}
