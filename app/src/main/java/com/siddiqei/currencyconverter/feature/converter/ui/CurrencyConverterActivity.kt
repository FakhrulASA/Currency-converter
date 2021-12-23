package com.siddiqei.currencyconverter.feature.converter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siddiqei.currencyconverter.databinding.ActivityMainBinding

import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.siddiqei.currencyconverter.R


class CurrencyConverterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: CurrencyConverterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        model.getCurrencyUnit(this)
        model.listOfCurrency.observe(this){
            val arrayAdapter = initAdapter(it)
            initSpinner(arrayAdapter)
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
        ) //Your resource name
    }
}