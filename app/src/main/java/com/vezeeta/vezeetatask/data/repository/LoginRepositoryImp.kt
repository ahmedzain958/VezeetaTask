package com.vezeeta.vezeetatask.data.repository

import com.vezeeta.vezeetatask.data.source.local.LocalDataSource
import com.vezeeta.vezeetatask.data.source.remote.RemoteDataSource
import com.vezeeta.vezeetatask.domain.model.User
import com.vezeeta.vezeetatask.domain.repository.LoginRepository

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class LoginRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : LoginRepository {

    override suspend fun login(email: String, password: String): User {
        val user = remoteDataSource.login(email, password)
        localDataSource.cacheUser(user)
        return user
    }

}