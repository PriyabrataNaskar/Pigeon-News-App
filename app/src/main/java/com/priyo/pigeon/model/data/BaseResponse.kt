package com.priyo.pigeon.model.data

data class BaseResponse(
    val articles: List<ArticleResponse>,
    val status: String,
    val totalResults: Int,
)
