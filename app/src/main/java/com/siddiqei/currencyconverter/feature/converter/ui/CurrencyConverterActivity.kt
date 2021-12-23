package com.siddiqei.currencyconverter.feature.converter.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.siddiqei.currencyconverter.R
import com.siddiqei.currencyconverter.base.BaseConverter
import com.siddiqei.currencyconverter.databinding.ActivityMainBinding
import com.siddiqei.currencyconverter.feature.converter.adapter.BalanceAdapter
import com.siddiqei.currencyconverter.model.ConverterRequestModel
import com.siddiqei.currencyconverter.util.InternetChecker.isInternetAvailable
import kotlin.math.abs


class CurrencyConverterActivity : AppCompatActivity(), BaseConverter {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BalanceAdapter
    var gson: Gson = Gson()
    private val model: CurrencyConverterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        getCurrencyUnit()
        initAdapter()
        binding.tvButtonSubmit.setOnClickListener {
            if (isInternetAvailable(this)) {
                convertCurrency()
            } else {
                Toast.makeText(this, "No Internet Available", Toast.LENGTH_LONG).show()
            }
        }

    }
    /**
     * Here we are initializing our recyclerviewr adapter
     */
    private fun initAdapter() {
        try {
            adapter = BalanceAdapter(
                this,
                listOf("100.00 USD", "8500.0 BDT", "0.0 EURO", "10.00 CAD", "10000.0 KAZ")
            )
            val layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.layoutManager = layoutManager
            binding.recyclerView.adapter = adapter
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getCurrencyUnit() {
        model.getCurrencyUnit(this)
        model.listOfCurrency.observe(this) {
            val arrayAdapter = initSpinnerAdapter(it)
            initSpinner(arrayAdapter)
        }
    }

    /**
     * This function for converting the currency according to the requirement
     */
    private fun convertCurrency() {
        try {
            model.getConvertedValue(ConverterRequestModel().apply {
                apiKey = "7435347754-514c9fc349-r4km25"
                from = binding.spinner.selectedItem.toString()
                to = binding.spinner2.selectedItem.toString()
                amount = abs(binding.editTextTextPersonName.text.toString().toDouble()).toString()
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Here we are initializing our layout with viewbinding
     */
    private fun initLayout() {
        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Here we are initializing our soinners
     */
    private fun initSpinner(arrayAdapter: ArrayAdapter<String>) {
        binding.spinner.adapter = arrayAdapter
        binding.spinner2.adapter = arrayAdapter
    }
    /**
     * Here we are initializing our spinner adapter
     */
    private fun initSpinnerAdapter(data: List<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            this, R.layout.spinner_item,
            data
        )
    }

}