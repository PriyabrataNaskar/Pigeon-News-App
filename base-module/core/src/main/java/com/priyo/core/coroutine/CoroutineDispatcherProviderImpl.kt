package com.priyo.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoroutineDispatcherProviderImpl : CoroutineDispatcherProvider {
    override val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.IO

    override suspend fun <T> switchToDefault(
        block: suspend CoroutineScope.() -> T,
    ): T =
        withContext(dispatcherDefault) {
            block()
        }

    override suspend fun <T> switchToIO(block: suspend CoroutineScope.() -> T): T =
        withContext(dispatcherIO) {
            block()
        }
}
