package com.priyo.corenetwork.dto

import com.google.gson.annotations.SerializedName

data class BaseDto<T>(
    @SerializedName("articles")
    val list: List<T>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
)
