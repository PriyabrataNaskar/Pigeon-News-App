package com.priyo.pigeon.model.data

data class BaseResponse<T>(
    val list: List<T>,
    val status: String,
    val totalResults: Int,
)
