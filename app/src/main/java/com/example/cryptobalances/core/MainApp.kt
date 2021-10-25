package com.example.cryptobalances.core

import android.app.Application
import com.example.cryptobalances.core.di.appComponenet
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application(){

    fun provideComponent() = appComponenet
    fun configureDi() = startKoin {
        androidLogger()
        androidContext(this@MainApp)
        modules(provideComponent())
    }

    override fun onCreate() {
        super.onCreate()

        configureDi()
    }
}