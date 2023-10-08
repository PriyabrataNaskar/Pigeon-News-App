package com.priyo.news.data.source.remotedatasource

import com.priyo.core.result.Result
import com.priyo.news.domain.model.Article

interface INewsRemoteDataSource {

    suspend fun getTopNews(
        pageNumber: Int,
        countryCode: String,
    ): Result<List<Article>>
}
