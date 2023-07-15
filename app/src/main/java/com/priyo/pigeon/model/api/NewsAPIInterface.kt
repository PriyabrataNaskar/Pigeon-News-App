package com.priyo.pigeon.model.api

import com.priyo.pigeon.BuildConfig
import com.priyo.pigeon.model.data.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Priyabrata Naskar on 04-04-2022.
 */
interface NewsAPIInterface {

    /**
     * Search for only top articles
     */
    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("apiKey") apiKey: String = "BuildConfig.API_KEY",
        @Query("page") pageNumber: Int = 1,
        @Query("country") countryCode: String = "us",
    ): Response<ResponseModel>
}
