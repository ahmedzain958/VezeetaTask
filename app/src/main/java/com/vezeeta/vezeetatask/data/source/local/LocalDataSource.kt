package com.vezeeta.vezeetatask.data.source.local

import com.vezeeta.vezeetatask.domain.model.User

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface LocalDataSource {
    suspend fun cacheUser(user: User)
    suspend fun countUsers(): Int
    suspend fun deleteUser()
}