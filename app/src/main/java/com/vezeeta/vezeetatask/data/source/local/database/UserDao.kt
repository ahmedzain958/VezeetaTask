package com.vezeeta.vezeetatask.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vezeeta.vezeetatask.data.source.local.database.DatabaseConstants.TABLE_USER
import com.vezeeta.vezeetatask.domain.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("Delete FROM $TABLE_USER")
    suspend fun deleteUser()

    @Query("SELECT COUNT(*) FROM $TABLE_USER")
    suspend fun countUsers(): Int

    @Insert
    suspend fun insertAll(users: List<User>)
}