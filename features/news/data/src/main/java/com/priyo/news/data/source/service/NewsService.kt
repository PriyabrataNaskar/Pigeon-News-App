package com.priyo.news.data.source.service

import com.priyo.corenetwork.dto.BaseDto
import com.priyo.news.data.BuildConfig
import com.priyo.news.data.source.dto.ArticleDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    /**
     * Search for only top articles
     */
    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("country") countryCode: String = "us",
    ): Response<BaseDto<ArticleDto>>
}
