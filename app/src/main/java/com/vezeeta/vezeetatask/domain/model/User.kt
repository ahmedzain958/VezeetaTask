package com.vezeeta.vezeetatask.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vezeeta.vezeetatask.data.source.local.database.DatabaseConstants.TABLE_USER

@Entity(tableName = TABLE_USER)
data class User(
    @PrimaryKey(autoGenerate = true)
    var Id: Int?,
    @ColumnInfo(name = "location")
    var location: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "user_key")
    var userKey: String?,
    @ColumnInfo(name = "user_name")
    var userName: String?,
    @ColumnInfo(name = "user_token")
    var userToken: String?
) {
    constructor() : this(
        null, null, null, null, null, null
    )
}