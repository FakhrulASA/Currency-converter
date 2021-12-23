package com.siddiqei.currencyconverter.feature.converter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siddiqei.currencyconverter.databinding.ActivityMainBinding
import com.siddiqei.currencyconverter.databinding.ContentItemBalanceBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class BalanceAdapter(var context: Context,var balanceList:List<String>):
    RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder>() {
    class BalanceViewHolder(private val binding: ContentItemBalanceBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(list:List<String>, postition:Int){
            binding.balanceText.text= list[postition]
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BalanceAdapter.BalanceViewHolder =  BalanceViewHolder(ContentItemBalanceBinding.inflate(
    LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BalanceAdapter.BalanceViewHolder, position: Int) {
        holder.bind(balanceList,position)
    }

    override fun getItemCount(): Int = balanceList.size
}