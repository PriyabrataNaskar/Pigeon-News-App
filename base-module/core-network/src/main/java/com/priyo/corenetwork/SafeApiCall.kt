package com.priyo.corenetwork

import com.priyo.corenetwork.result.NetworkError
import com.priyo.corenetwork.result.NetworkException
import com.priyo.corenetwork.result.NetworkResult
import com.priyo.corenetwork.result.NetworkSuccess
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> safeApiCall(
    execute: suspend () -> Response<T>,
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            NetworkSuccess(body)
        } else {
            NetworkError(
                code = response.code(),
                message = response.message(),
            )
        }
    } catch (e: HttpException) {
        NetworkError(
            code = e.code(),
            message = e.message(),
        )
    } catch (throwable: Throwable) {
        NetworkException(throwable)
    }
}
