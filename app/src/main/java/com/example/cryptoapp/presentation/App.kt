package com.example.cryptoapp.presentation

import android.app.Application
import com.example.cryptoapp.di.DaggerApplicationComponent

class App: Application() {
    val component = DaggerApplicationComponent.factory().create(this, this)
}