package com.priyo.pigeon.model.repository

import com.priyo.pigeon.model.api.NewsResponseInstance
import com.priyo.pigeon.model.data.ArticleResponse
import com.priyo.pigeon.model.data.BaseResponse
import retrofit2.Response

/**
 * Created by Priyabrata Naskar on 05-04-2022.
 */
class NewsRepository {
    suspend fun getTopNews(): Response<BaseResponse<ArticleResponse>> {
        return NewsResponseInstance.apiInstance.getTopNews()
    }
}
