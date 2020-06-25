package com.vezeeta.vezeetatask.data.repository

import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.data.AppResources

class ResourcesRepository(private val appResources: AppResources) {
    fun getNetworkExceptionMessage(): String = appResources.getString(R.string.no_internet_connection)
}