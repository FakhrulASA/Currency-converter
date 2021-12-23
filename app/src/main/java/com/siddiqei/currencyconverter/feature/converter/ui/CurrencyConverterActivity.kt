package com.siddiqei.currencyconverter.feature.converter.ui

import android.app.AlertDialog
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
import android.content.DialogInterface
import com.siddiqei.currencyconverter.util.InternetChecker.isInternetAvailable
import android.view.View
import androidx.core.content.ContentProviderCompat

import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.core.content.ContentProviderCompat.requireContext
import com.siddiqei.currencyconverter.feature.converter.adapter.BalanceAdapter


class CurrencyConverterActivity : AppCompatActivity(), BaseConverter {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BalanceAdapter
    var gson:Gson= Gson()
    private val model: CurrencyConverterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        getCurrencyUnit()
        adapter= BalanceAdapter(this, listOf("100.00 USD", "8500.0 BDT", "0.0 EURO", "10.00 CAD", "10000.0 KAZ"))
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter=adapter
        binding.tvButtonSubmit.setOnClickListener {
            if(isInternetAvailable(this)){
                convertCurrency()
            }else{
                Toast.makeText(this,"No Internet Available",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun getCurrencyUnit() {
        model.getCurrencyUnit(this)
        model.listOfCurrency.observe(this) {
            val arrayAdapter = initAdapter(it)
            initSpinner(arrayAdapter)
        }
    }

    private fun convertCurrency() {
        model.getConvertedValue(ConverterRequestModel().apply {
            apiKey = "7435347754-514c9fc349-r4km25"
            from = binding.spinner.selectedItem.toString()
            to = binding.spinner2.selectedItem.toString()
            amount = binding.editTextTextPersonName.text.toString()
        })

        model.convertedValue.observe(this) {
            var objectList: HashMap<String, String> =
                gson.fromJson(it, object : TypeToken<HashMap<String, String>>() {}.type)
            binding.editTextTextPersonName2.setText(objectList[binding.spinner2.selectedItem.toString()])
            objectList.clear()
            AlertDialog.Builder(this)
                .setTitle("Currency converted")
                .setMessage("You have converted ${binding.editTextTextPersonName.text} ${binding.spinner.selectedItem} to ${binding.editTextTextPersonName2.text} ${binding.spinner2.selectedItem}") // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Done",
                    DialogInterface.OnClickListener { dialog, which ->
                        // Continue with delete operation
                    }) // A null listener allows the button to dismiss the dialog and take no further action.
                .show()
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