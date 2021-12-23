package com.siddiqei.currencyconverter.interactor

import com.siddiqei.currencyconverter.model.ConverterRequestModel
import com.siddiqei.currencyconverter.model.ConverterResponseModel
import com.siddiqei.currencyconverter.repository.ConverterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class ConverterUseCase {
    var converterRepository: ConverterRepository = ConverterRepository()
    private val job = CoroutineScope(Dispatchers.IO)
    fun invoke(
        converterRequestModel: ConverterRequestModel,
        isSuccess: (ConverterResponseModel) -> Unit,
        isFailed: (String) -> Unit
    ) {
        val converter = converterRepository.getPost(
            converterRequestModel.from,
            converterRequestModel.to,
            converterRequestModel.apiKey,
            converterRequestModel.amount,

            )
        converter.execute().apply {
            if(this.isSuccessful){
                isSuccess.invoke(this.body()!!)
            }else{
                isFailed.invoke(this.errorBody().toString())
            }
        }
    }

    fun cancel(onCancelled: () -> Unit) {
        job.cancel("Job Cancelled")
        onCancelled.invoke()
    }
}