package com.siddiqei.currencyconverter.network

import com.siddiqei.currencyconverter.model.ConverterResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("convert")
    fun getPost(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("api_key") apiKey: String,
        @Query("amount") amount: String
    ): Call<ConverterResponseModel>
}