package com.siddiqei.currencyconverter.feature.converter.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siddiqei.currencyconverter.R

class CurrencyConverterViewModel: ViewModel() {
    var listOfCurrency:MutableLiveData<List<String>> = MutableLiveData()

    fun getCurrencyUnit(context: Context){
        listOfCurrency.postValue(context.resources.getStringArray(R.array.currency).toList())
    }
}