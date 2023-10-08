package com.priyo.news.data.source.remotedatasource

import com.priyo.core.result.DomainException
import com.priyo.core.result.Error
import com.priyo.core.result.Result
import com.priyo.core.result.Success
import com.priyo.corenetwork.result.onException
import com.priyo.corenetwork.result.onFailure
import com.priyo.corenetwork.result.onSuccess
import com.priyo.corenetwork.safeApiCall
import com.priyo.news.data.mapper.NewsMapper
import com.priyo.news.data.source.service.NewsService
import com.priyo.news.domain.model.Article
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsServices: NewsService,
) : INewsRemoteDataSource {

    override suspend fun getTopNews(
        pageNumber: Int,
        countryCode: String,
    ): Result<List<Article>> {
        var result: Result<List<Article>>? = null
        safeApiCall {
            newsServices.getTopNews(
                pageNumber = pageNumber,
                countryCode = countryCode,
            )
        }.onSuccess {
            result = Success(
                NewsMapper.toArticleList(it.list),
            )
        }.onFailure { code, message ->
            result = Error(
                code = code,
                message = message,
            )
        }.onException {
            result = DomainException(
                throwable = it,
            )
        }
        return result ?: DomainException(Throwable(message = null))
    }
}
