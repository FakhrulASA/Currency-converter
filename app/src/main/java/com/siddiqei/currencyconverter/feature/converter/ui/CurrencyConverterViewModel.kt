package com.siddiqei.currencyconverter.feature.converter.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siddiqei.currencyconverter.R
import com.siddiqei.currencyconverter.interactor.ConverterUseCase
import com.siddiqei.currencyconverter.model.ConverterRequestModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyConverterViewModel: ViewModel() {
    var listOfCurrency:MutableLiveData<List<String>> = MutableLiveData()
    var convertedValue: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var converterUseCase:ConverterUseCase= ConverterUseCase()
    val error: MutableLiveData<String> = MutableLiveData()
    fun getCurrencyUnit(context: Context){
        listOfCurrency.postValue(context.resources.getStringArray(R.array.currency).toList())
    }

    fun getConvertedValue(converterRequestModel: ConverterRequestModel){
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            converterUseCase.invoke(converterRequestModel, {
                error.postValue("Cannot proceed the request")
                convertedValue.postValue(it.result.toString())
                isLoading.postValue(false)
            }, {
                error.postValue("Cannot proceed the request")
                isLoading.postValue(false)
            })
        }
    }
}