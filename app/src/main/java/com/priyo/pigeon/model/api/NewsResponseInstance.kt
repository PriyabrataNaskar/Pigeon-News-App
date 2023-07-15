package com.priyo.pigeon.model.api

import com.priyo.pigeon.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Priyabrata Naskar on 04-04-2022.
 */
class NewsResponseInstance {
    companion object {
        private val retrofit by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            logging.redactHeader("Authorization")
            logging.redactHeader("Cookie")

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val apiInstance: NewsService by lazy {
            retrofit.create(NewsService::class.java)
        }
    }
}
