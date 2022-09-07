package com.example.examplecryptoapp.di

import android.app.Application
import com.example.examplecryptoapp.data.database.AppDatabase
import com.example.examplecryptoapp.data.database.CoinInfoDao
import com.example.examplecryptoapp.data.network.ApiFactory
import com.example.examplecryptoapp.data.network.ApiService
import com.example.examplecryptoapp.data.repository.CoinRepositoryImpl
import com.example.examplecryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindRepository(impl: CoinRepositoryImpl): CoinRepository


    companion object{
        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application): CoinInfoDao{
            return  AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}