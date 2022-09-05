package com.example.examplecryptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinInfoUseCase(private val coinRepository: CoinRepository) {
    operator fun invoke(fromSymbol: String) = coinRepository.getCoinInfo(fromSymbol)
}