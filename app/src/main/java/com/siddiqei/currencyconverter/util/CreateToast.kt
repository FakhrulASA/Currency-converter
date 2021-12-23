package com.siddiqei.currencyconverter.util

import android.content.Context
import android.widget.Toast

object CreateToast {
    fun showToast(activity:Context,s:String){
        Toast.makeText(activity,s,Toast.LENGTH_LONG).show()
    }
}