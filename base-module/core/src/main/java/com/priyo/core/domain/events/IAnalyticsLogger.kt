package com.priyo.core.domain.events

/**
 * Keeping IAnalyticsLogger in core module ensures that we don't need Analytics Module Dependency in every feature module
 */
interface IAnalyticsLogger {
    fun trackEvent(
        eventName: String,
        eventProperties: Map<String, Any>,
    )
}
