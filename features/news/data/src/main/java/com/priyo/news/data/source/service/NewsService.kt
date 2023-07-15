package com.priyo.news.data.source.service

import com.priyo.corenetwork.dto.BaseDto
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
        @Query("apiKey") apiKey: String = "84a9ce0f3b9040bf8edf144d10a95708", // todo: remove hardcoded api key
        @Query("page") pageNumber: Int = 1,
        @Query("country") countryCode: String = "us",
    ): Response<BaseDto<ArticleDto>>
}
