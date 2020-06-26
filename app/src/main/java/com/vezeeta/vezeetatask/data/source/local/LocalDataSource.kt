package com.vezeeta.vezeetatask.data.source.local

import com.vezeeta.vezeetatask.domain.model.User
import io.reactivex.Completable

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface LocalDataSource {
    suspend fun cacheUser(user: User)
}