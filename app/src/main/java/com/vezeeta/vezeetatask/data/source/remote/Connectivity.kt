package com.vezeeta.vezeetatask.data.source.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Connectivity {
    //todo handle network connectivity for >=android 10
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}
