package com.vezeeta.vezeetatask.di

import com.vezeeta.vezeetatask.BuildConfig
import com.vezeeta.vezeetatask.data.source.remote.ConnectivityInterceptor
import com.vezeeta.vezeetatask.data.source.remote.ConnectivityInterceptorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Ahmed Zain on 6/24/2020.
 */

val networkModule = module {
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get()) }
    single { createOkHttpClient(get()) }
    single { createRetrofit(get(), BuildConfig.BASE_URL) }
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createOkHttpClient(connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(connectivityInterceptor)
        .build()
}