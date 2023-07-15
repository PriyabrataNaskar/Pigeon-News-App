package com.priyo.corenetwork

import retrofit2.Response

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
