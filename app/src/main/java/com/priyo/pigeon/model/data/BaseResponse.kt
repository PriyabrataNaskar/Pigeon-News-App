package com.priyo.pigeon.model.data

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("articles")
    val list: List<T>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
)
