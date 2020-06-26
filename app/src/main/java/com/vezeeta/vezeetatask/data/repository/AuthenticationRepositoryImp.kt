package com.vezeeta.vezeetatask.data.repository

import android.util.Log
import com.vezeeta.vezeetatask.data.source.local.LocalDataSource
import com.vezeeta.vezeetatask.data.source.remote.RemoteDataSource
import com.vezeeta.vezeetatask.domain.model.User
import com.vezeeta.vezeetatask.domain.repository.AuthenticationRepository

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class AuthenticationRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AuthenticationRepository {
    private val TAG = AuthenticationRepositoryImp::class.java.name

    override suspend fun login(email: String, password: String): User {
        val user = remoteDataSource.login(email, password)
        localDataSource.cacheUser(user)
        return user
    }

    override suspend fun isLoggedUser(): Boolean {
        val usersCount = localDataSource.countUsers()
        Log.d(TAG, "count users $usersCount")
        return usersCount != 0
    }

    override suspend fun logOut(): Boolean {
        localDataSource.deleteUser()
        return true
    }

}