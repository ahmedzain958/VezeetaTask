package com.vezeeta.vezeetatask.di

import androidx.room.Room
import com.vezeeta.vezeetatask.data.source.local.database.DatabaseConstants.USERS_DATABASE
import com.vezeeta.vezeetatask.data.source.local.database.UsersDatabase
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/26/2020.
 */
val localModule = module {
    single {
        Room.databaseBuilder(get(), UsersDatabase::class.java, USERS_DATABASE).build()
    }
    single {
        get<UsersDatabase>().userDao()
    }
}