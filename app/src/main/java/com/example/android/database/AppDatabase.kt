package com.example.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.database.dao.GroupDao
import com.example.android.database.dao.StudentDao
import com.example.android.database.entity.Group
import com.example.android.database.entity.Student

@Database(entities = [Student::class, Group::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun groupDao(): GroupDao
}
