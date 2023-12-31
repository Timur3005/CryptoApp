package com.example.cryptoapp.di

import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @ApplicationScope
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository
}