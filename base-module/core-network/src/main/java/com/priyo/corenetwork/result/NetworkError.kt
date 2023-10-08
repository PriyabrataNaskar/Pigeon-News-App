package com.priyo.corenetwork.result

class NetworkError<T : Any>(
    val code: Int,
    val message: String? = null,
) : NetworkResult<T>
