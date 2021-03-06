package com.vezeeta.vezeetatask

import android.app.Application
import com.vezeeta.vezeetatask.di.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class VezeetaApplication : Application() {
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger()
            androidContext(this@VezeetaApplication)
            modules(
                listOf(
                    networkModule,
                    appModule,
                    offersModule,
                    authenticationModule,
                    localModule
                )
            )
        }
    }
}