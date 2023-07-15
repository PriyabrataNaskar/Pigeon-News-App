package com.priyo.core.domain

import com.priyo.core.domain.ErrorConstants.NO_INTERNET
import com.priyo.core.domain.ErrorConstants.UNKNOWN_ERROR
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend inline fun <T> executeSafeCall(
    block: () -> T,
    error: (Exception) -> T,
): T {
    return try {
        block.invoke()
    } catch (e: Exception) {
        val textRes = when (e) {
            is SocketTimeoutException -> Exception(
                UNKNOWN_ERROR,
            )
            is UnknownHostException -> Exception(
                NO_INTERNET,
            )
            is IOException -> Exception(UNKNOWN_ERROR)
            else -> Exception(e.message.orEmpty())
        }
        error.invoke(textRes)
    }
}
