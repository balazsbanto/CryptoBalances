package com.example.cryptobalances.core

import android.app.Application
import com.example.cryptobalances.BuildConfig
import com.example.cryptobalances.core.di.appComponenet
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApp : Application(){

    fun provideComponent() = appComponenet

    fun configureDi() = startKoin {
        androidLogger()
        androidContext(this@MainApp)
        modules(provideComponent())
    }

    fun configureLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun onCreate() {
        super.onCreate()

        configureDi()
        configureLogging()
    }
}