package com.siddiqei.currencyconverter.feature.converter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siddiqei.currencyconverter.databinding.ActivityMainBinding

import android.widget.ArrayAdapter
import com.siddiqei.currencyconverter.R


class CurrencyConverterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        val arrayAdapter = initAdapter()
        initSpinner(arrayAdapter)
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

    private fun initAdapter(): ArrayAdapter<String> {
        val arrayAdapter = ArrayAdapter(
            this, R.layout.spinner_item,
            resources.getStringArray(R.array.currency)
        ) //Your resource name
        return arrayAdapter
    }
}