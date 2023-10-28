package com.example.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.workers.RefreshDataWorker
import com.example.cryptoapp.di.ApplicationScope
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinInfoDao: CoinInfoDao,
    private val coinMapper: CoinMapper
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return MediatorLiveData<List<CoinInfo>>().apply {
            addSource(coinInfoDao.getPriceList()) {
                value = coinMapper.mapDbModelListToEntityList(it)
            }
        }

    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return MediatorLiveData<CoinInfo>().apply {
            addSource(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
                value = coinMapper.mapDbModelToEntity(it)
            }
        }

    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}