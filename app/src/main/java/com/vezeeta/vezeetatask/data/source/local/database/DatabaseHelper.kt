package com.vezeeta.vezeetatask.data.source.local.database

import com.vezeeta.vezeetatask.domain.model.User

/**
 * Created by Ahmed Zain on 6/26/2020.
 */
interface DatabaseHelper {
    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)
}