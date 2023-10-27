package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideCoinInfoDao(application: Application) =
        AppDatabase.getInstance(application).coinPriceInfoDao()
}