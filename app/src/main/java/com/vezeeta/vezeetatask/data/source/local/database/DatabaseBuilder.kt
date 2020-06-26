package com.vezeeta.vezeetatask.data.source.local.database

import android.content.Context
import androidx.room.Room

/**
 * Created by Ahmed Zain on 6/26/2020.
 */
object DatabaseBuilder {

    private var INSTANCE: UsersDatabase? = null

    fun getInstance(context: Context): UsersDatabase {
        if (INSTANCE == null) {
            synchronized(UsersDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            "users-db"
        ).build()

}