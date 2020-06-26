package com.vezeeta.vezeetatask.domain.repository

import com.vezeeta.vezeetatask.domain.model.User

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface AuthenticationRepository {
    suspend fun login(email: String, password: String): User
    suspend fun isLoggedUser(): Boolean
    suspend fun logOut(): Boolean
}