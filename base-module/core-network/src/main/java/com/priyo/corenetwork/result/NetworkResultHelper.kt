package com.priyo.corenetwork.result

suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit,
): NetworkResult<T> = apply {
    if (this is NetworkSuccess<T>) {
        executable(data)
    }
}

suspend fun <T : Any> NetworkResult<T>.onFailure(
    executable: suspend (code: Int, message: String?) -> Unit,
): NetworkResult<T> = apply {
    if (this is NetworkError<T>) {
        executable(code, message)
    }
}

suspend fun <T : Any> NetworkResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit,
): NetworkResult<T> = apply {
    if (this is NetworkException<T>) {
        executable(throwable)
    }
}
