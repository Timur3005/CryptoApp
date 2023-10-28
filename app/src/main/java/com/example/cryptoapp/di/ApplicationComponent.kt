package com.example.cryptoapp.di

import android.app.Application
import android.content.Context
import com.example.cryptoapp.presentation.App
import com.example.cryptoapp.presentation.CoinDetailFragment
import com.example.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: App)

    @Component.Factory
    interface ApplicationComponentFactory{

        fun create(
            @BindsInstance application: Application,
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}