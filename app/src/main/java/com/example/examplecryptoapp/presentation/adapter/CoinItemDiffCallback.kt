package com.example.examplecryptoapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.examplecryptoapp.domain.CoinInfo

class CoinItemDiffCallback: DiffUtil.ItemCallback<CoinInfo>() {


    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}