package com.vezeeta.vezeetatask.data.repository

import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.data.AppResources

class ResourcesRepository(private val appResources: AppResources) {
    fun getNetworkExceptionMessage(): String =
        appResources.getString(R.string.no_internet_connection_error_message)

    fun getSocketTimeoutExceptionMessage(): String =
        appResources.getString(R.string.socket_timeout_error_message)

    fun getUnAuthorizedExceptionMessage(): String =
        appResources.getString(R.string.unauthorized_error_message)
}