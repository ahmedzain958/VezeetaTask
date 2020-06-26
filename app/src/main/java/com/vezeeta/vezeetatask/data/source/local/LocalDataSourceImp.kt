package com.vezeeta.vezeetatask.data.source.local

import com.vezeeta.vezeetatask.data.source.local.database.UserDao
import com.vezeeta.vezeetatask.domain.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class LocalDataSourceImp(
    private val userDao: UserDao
) : LocalDataSource {
    override suspend fun cacheUser(user: User) {
        GlobalScope.launch {
            userDao.insert(user)
        }
    }

    override suspend fun countUsers(): Int {
        val countUsers = userDao.countUsers()
        return countUsers
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }
}