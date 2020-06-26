package com.vezeeta.vezeetatask.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vezeeta.vezeetatask.domain.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("Delete FROM user")
    suspend fun deleteUser()

    @Query("SELECT COUNT(*) FROM user")
    suspend fun countUsers(): Int

    @Insert
    suspend fun insertAll(users: List<User>)
}