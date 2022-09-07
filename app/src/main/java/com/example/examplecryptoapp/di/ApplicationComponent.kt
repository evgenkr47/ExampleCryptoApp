package com.example.examplecryptoapp.di

import android.app.Application
import com.example.examplecryptoapp.presentation.CoinApp
import com.example.examplecryptoapp.presentation.CoinDetailFragment
import com.example.examplecryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component
@ApplicationScope
@Component(modules = [DataModule::class, ViewModelsModule::class, WorkerModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)
    fun inject(application: CoinApp)

    @Component.Factory
    interface ApplicationComponentFactory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}