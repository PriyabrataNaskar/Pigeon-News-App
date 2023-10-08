package com.priyo.core.result

sealed interface Result<T : Any>

class Success<T : Any>(val data: T) : Result<T>
class Error<T : Any>(
    val code: Int? = null,
    val message: String? = null,
) : Result<T>
class DomainException<T : Any>(val throwable: Throwable?) : Result<T>
