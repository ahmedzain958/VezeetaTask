package com.vezeeta.vezeetatask.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vezeeta.vezeetatask.data.source.local.database.DatabaseConstants.DATABASE_VERSION
import com.vezeeta.vezeetatask.domain.model.User

@Database(entities = [User::class], version = DATABASE_VERSION, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}