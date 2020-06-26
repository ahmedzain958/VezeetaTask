package com.vezeeta.vezeetatask

import android.app.Application
import com.vezeeta.vezeetatask.di.appModule
import com.vezeeta.vezeetatask.di.loginModule
import com.vezeeta.vezeetatask.di.networkModule
import com.vezeeta.vezeetatask.di.offersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class VezeetaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger()
            androidContext(this@VezeetaApplication)
            modules(listOf(networkModule, appModule, offersModule, loginModule))
        }
    }
}