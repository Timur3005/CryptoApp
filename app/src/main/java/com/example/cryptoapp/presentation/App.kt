package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.workers.RefreshDataWorkerFactory
import com.example.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class App: Application(), Configuration.Provider{

    val component = DaggerApplicationComponent.factory().create(this, this)

    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(workerFactory).build()
    }
}