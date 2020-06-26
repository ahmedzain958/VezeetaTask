package com.vezeeta.vezeetatask.data.source.local

import com.vezeeta.vezeetatask.data.source.local.database.DatabaseHelper
import com.vezeeta.vezeetatask.domain.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class LocalDataSourceImp(
    private val databaseHelper: DatabaseHelper
) : LocalDataSource {
    override suspend fun cacheUser(user: User) {
        GlobalScope.launch {
            try {
                val usersFromDb = databaseHelper.getUsers()

            } catch (e: Exception) {
                // handler error
            }
        }
    }
}