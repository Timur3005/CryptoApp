package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.network.ApiFactory
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @ApplicationScope
    @Provides
    fun provideCoinInfoDao(application: Application) =
        AppDatabase.getInstance(application).coinPriceInfoDao()

    @Provides
    fun provideApiService() = ApiFactory.apiService
}