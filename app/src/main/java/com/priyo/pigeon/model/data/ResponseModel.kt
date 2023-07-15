package com.priyo.pigeon.model.data

data class ResponseModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
)
