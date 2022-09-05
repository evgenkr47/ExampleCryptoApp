package com.example.examplecryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.examplecryptoapp.data.repository.CoinRepositoryImpl
import com.example.examplecryptoapp.domain.GetCoinInfoListUseCase
import com.example.examplecryptoapp.domain.GetCoinInfoUseCase
import com.example.examplecryptoapp.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CoinRepositoryImpl(application)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()
    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }

}