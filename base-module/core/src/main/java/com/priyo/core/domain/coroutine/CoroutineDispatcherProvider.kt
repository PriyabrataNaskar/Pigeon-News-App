package com.priyo.core.domain.coroutine

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    val dispatcherDefault: CoroutineDispatcher
    val dispatcherIO: CoroutineDispatcher
}
