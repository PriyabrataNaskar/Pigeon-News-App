package com.priyo.core.domain

import com.priyo.core.domain.ErrorConstants.NO_INTERNET
import com.priyo.core.domain.ErrorConstants.UNKNOWN_ERROR
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> executeSafeCall(
    execute: suspend () -> T,
    onError: suspend (Exception) -> T,
): T {
    return try {
        execute()
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
        onError(textRes)
    }
}
