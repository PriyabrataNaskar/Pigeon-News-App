package com.priyo.news.domain.repository

import com.priyo.core.result.NetworkResult
import com.priyo.news.domain.model.Article

interface NewsRepository {

    suspend fun getTopNews(
        pageNumber: Int,
        countryCode: String,
    ): NetworkResult<List<Article>>
}
