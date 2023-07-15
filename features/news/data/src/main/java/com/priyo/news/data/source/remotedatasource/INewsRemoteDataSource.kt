package com.priyo.news.data.source.remotedatasource

import com.priyo.corenetwork.NetworkResult
import com.priyo.news.domain.model.Article

interface INewsRemoteDataSource {

    suspend fun getTopNews(
        pageNumber: Int,
        countryCode: String,
    ): NetworkResult<List<Article>>
}
