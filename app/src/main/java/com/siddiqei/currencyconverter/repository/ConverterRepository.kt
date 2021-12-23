package com.siddiqei.currencyconverter.repository

import com.siddiqei.currencyconverter.model.ConverterResponseModel
import com.siddiqei.currencyconverter.network.ApiClient
import com.siddiqei.currencyconverter.network.ApiInterface
import retrofit2.Call

class ConverterRepository:ApiInterface{
    override fun getPost(
        from: String,
        to: String,
        apiKey: String,
        amount: String
    ): Call<ConverterResponseModel> = ApiClient.api.getPost(from,to,apiKey,amount)
}