package com.example.examplecryptoapp.data.workers

import android.content.Context
import androidx.work.*
import com.example.examplecryptoapp.data.database.AppDatabase
import com.example.examplecryptoapp.data.database.CoinInfoDao
import com.example.examplecryptoapp.data.mapper.CoinMapper
import com.example.examplecryptoapp.data.network.ApiFactory
import com.example.examplecryptoapp.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val mapper: CoinMapper,
    private val apiService: ApiService,
    private val coinInfoDao: CoinInfoDao
): CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {

        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map {
                    mapper.mapDtoToDbModel(it)
                }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
                throw RuntimeException("Exception $e")
            }
            delay(10000)
        }
    }

    companion object{
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    class Factory @Inject constructor(
        private val mapper: CoinMapper,
        private val apiService: ApiService,
        private val coinInfoDao: CoinInfoDao
    ): ChildWorkerFactory{
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                mapper,
                apiService,
                coinInfoDao
            )
        }

    }
}