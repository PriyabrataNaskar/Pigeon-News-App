package com.priyo.core.domain.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProviderImpl : CoroutineDispatcherProvider {
    override val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
}
