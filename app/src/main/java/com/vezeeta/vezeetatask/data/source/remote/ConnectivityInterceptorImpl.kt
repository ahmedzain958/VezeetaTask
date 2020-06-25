package com.vezeeta.vezeetatask.data.source.remote

import android.content.Context
import com.vezeeta.vezeetatask.data.source.remote.Connectivity.isConnected
import com.vezeeta.vezeetatask.data.source.remote.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(
    val context: Context?
) : ConnectivityInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        context?.let {
            if (!isConnected(it))
                throw NoConnectivityException()
        }

        return chain.proceed(chain.request())
    }


}