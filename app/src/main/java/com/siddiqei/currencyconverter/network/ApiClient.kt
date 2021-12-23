package com.siddiqei.currencyconverter.network

import com.siddiqei.currencyconverter.util.BaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BaseUrl.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: ApiInterface by lazy {
            retrofit.create(ApiInterface::class.java)
        }

    }
}