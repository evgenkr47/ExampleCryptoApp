package com.example.examplecryptoapp.di

import androidx.work.ListenableWorker
import com.example.examplecryptoapp.data.workers.ChildWorkerFactory
import com.example.examplecryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorker(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}