package com.siddiqei.currencyconverter.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class ConverterResponseModel {
    @SerializedName("base")
    @Expose
    val base: String? = null

    @SerializedName("amount")
    @Expose
    val amount: Int? = null

    @SerializedName("result")
    @Expose
    val result: Any = ""

    @SerializedName("ms")
    @Expose
    val ms: Int? = null

}