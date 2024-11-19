package com.example.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.database.entity.Student

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student): Long

    @Query("SELECT * FROM students")
    suspend fun getAllStudents(): List<Student>
}
