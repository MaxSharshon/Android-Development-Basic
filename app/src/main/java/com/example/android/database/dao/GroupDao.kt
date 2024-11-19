package com.example.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.database.entity.Group

@Dao
interface GroupDao {
    @Insert
    suspend fun insertGroup(group: Group): Long

    @Query("SELECT * FROM groups")
    suspend fun getAllGroups(): List<Group>
}
