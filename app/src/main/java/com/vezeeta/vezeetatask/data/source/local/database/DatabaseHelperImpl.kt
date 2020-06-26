package com.vezeeta.vezeetatask.data.source.local.database

import com.vezeeta.vezeetatask.domain.model.User

/**
 * Created by Ahmed Zain on 6/26/2020.
 */
class DatabaseHelperImpl(private val usersDatabase: UsersDatabase) : DatabaseHelper {

    override suspend fun getUsers(): List<User> = usersDatabase.userDao().getAll()

    override suspend fun insertAll(users: List<User>) = usersDatabase.userDao().insertAll(users)

}