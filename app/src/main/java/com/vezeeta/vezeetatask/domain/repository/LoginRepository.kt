package com.vezeeta.vezeetatask.domain.repository

import com.vezeeta.vezeetatask.domain.model.User

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface LoginRepository {
    suspend fun login(email: String, password: String): User
}