package com.siddiqei.currencyconverter.feature.converter.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.siddiqei.currencyconverter.R
import com.siddiqei.currencyconverter.base.BaseConverter
import com.siddiqei.currencyconverter.databinding.ActivityMainBinding
import com.siddiqei.currencyconverter.model.ConverterRequestModel


class CurrencyConverterActivity : AppCompatActivity(), BaseConverter {

    private lateinit var binding: ActivityMainBinding
    var gson:Gson= Gson()
    private val model: CurrencyConverterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        model.getCurrencyUnit(this)
        model.listOfCurrency.observe(this) {
            val arrayAdapter = initAdapter(it)
            initSpinner(arrayAdapter)
        }
        binding.tvButtonSubmit.setOnClickListener {
            model.getConvertedValue(ConverterRequestModel().apply {
                apiKey = "7435347754-514c9fc349-r4km25"
                from = binding.spinner.selectedItem.toString()
                to = binding.spinner2.selectedItem.toString()
                amount = binding.editTextTextPersonName.text.toString()
            })

            model.convertedValue.observe(this) {
                var objectList  : HashMap<String,String> = gson.fromJson(it, object : TypeToken<HashMap<String,String> >() {}.type)
                binding.editTextTextPersonName2.setText(objectList[binding.spinner2.selectedItem.toString()])
                objectList.clear()
            }
        }

    }

    private fun initLayout() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initSpinner(arrayAdapter: ArrayAdapter<String>) {
        binding.spinner.adapter = arrayAdapter
        binding.spinner2.adapter = arrayAdapter
    }

    private fun initAdapter(data: List<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            this, R.layout.spinner_item,
            data
        )
    }

}