package com.vezeeta.vezeetatask.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vezeeta.vezeetatask.domain.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}