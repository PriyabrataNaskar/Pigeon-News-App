package com.priyo.corenetwork

import com.priyo.corenetwork.NetworkConstants.NO_INTERNET
import com.priyo.corenetwork.NetworkConstants.UNKNOWN_ERROR
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(
    apiCallBlock: suspend () -> Response<T>,
): Result<T> {
    return try {
        val response = apiCallBlock.invoke()
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception(response.errorBody()?.string().toString()))
        }
    } catch (throwable: Throwable) {
        Result.failure(throwable)
    }
}

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
