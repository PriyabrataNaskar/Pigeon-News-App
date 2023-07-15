package com.priyo.pigeon.model.api

import com.priyo.pigeon.model.data.ArticleResponse
import com.priyo.pigeon.model.data.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Priyabrata Naskar on 04-04-2022.
 */
interface NewsService {

    /**
     * Search for only top articles
     */
    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("apiKey") apiKey: String = "BuildConfig.API_KEY",
        @Query("page") pageNumber: Int = 1,
        @Query("country") countryCode: String = "us",
    ): Response<BaseResponse<ArticleResponse>>
}
