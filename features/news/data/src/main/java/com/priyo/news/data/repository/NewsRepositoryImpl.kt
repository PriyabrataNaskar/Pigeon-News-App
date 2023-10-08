package com.priyo.news.data.repository

import com.priyo.core.result.Result
import com.priyo.news.data.source.remotedatasource.INewsRemoteDataSource
import com.priyo.news.domain.model.Article
import com.priyo.news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: INewsRemoteDataSource,
) : NewsRepository {

    override suspend fun getTopNews(
        pageNumber: Int,
        countryCode: String,
    ): Result<List<Article>> {
        return newsRemoteDataSource.getTopNews(
            pageNumber = pageNumber,
            countryCode = countryCode,
        )
    }
}
