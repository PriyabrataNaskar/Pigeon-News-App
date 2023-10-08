package com.priyo.news.domain.repository

import com.priyo.core.result.Result
import com.priyo.news.domain.model.Article

interface NewsRepository {

    suspend fun getTopNews(
        pageNumber: Int,
        countryCode: String,
    ): Result<List<Article>>
}
