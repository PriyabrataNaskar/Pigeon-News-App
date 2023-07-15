package com.priyo.core.result

sealed interface UiResult<out S, out E> {
    data class Success<S>(val data: S) : UiResult<S, Nothing>
    data class Error<E>(val error: E) : UiResult<Nothing, E>
}
