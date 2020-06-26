package com.vezeeta.vezeetatask.di

import com.vezeeta.vezeetatask.data.AppResources
import com.vezeeta.vezeetatask.data.repository.ResourcesRepository
import com.vezeeta.vezeetatask.data.source.local.LocalDataSource
import com.vezeeta.vezeetatask.data.source.local.LocalDataSourceImp
import com.vezeeta.vezeetatask.data.source.remote.RemoteDataSource
import com.vezeeta.vezeetatask.data.source.remote.RemoteDataSourceImp
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/24/2020.
 */


val appModule = module {
    single { AppResources(get()) }
    single { ResourcesRepository(get()) }
    single<RemoteDataSource> { RemoteDataSourceImp(get()) }
    single<LocalDataSource> { LocalDataSourceImp(get()) }
}
