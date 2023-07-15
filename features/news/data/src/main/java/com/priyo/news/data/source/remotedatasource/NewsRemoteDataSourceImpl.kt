package com.priyo.news.data.source.remotedatasource

import com.priyo.corenetwork.NetworkConstants.SOMETHING_WENT_WRONG
import com.priyo.corenetwork.NetworkResult
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
    ): NetworkResult<List<Article>> {
        safeApiCall {
            newsServices.getTopNews(
                pageNumber = pageNumber,
                countryCode = countryCode,
            )
        }.onSuccess {
            it.let {
                return NetworkResult.Success(
                    NewsMapper.toArticleList(it.list),
                )
            }
        }.onFailure {
            return NetworkResult.Error(
                throwable = it,
            )
        }
        return NetworkResult.Error(
            throwable = Exception(
                SOMETHING_WENT_WRONG,
            ),
        )
    }
}
