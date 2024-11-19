package com.example.android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.android.database.AppDatabase
import com.example.android.database.entity.Group
import com.example.android.database.entity.Student
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var groupNameInput: EditText
    private lateinit var studentNameInput: EditText
    private lateinit var studentSurnameInput: EditText
    private lateinit var studentAgeInput: EditText
    private lateinit var studentGroupInput: EditText
    private lateinit var studentList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-database").build()

        groupNameInput = findViewById(R.id.group_name)
        studentNameInput = findViewById(R.id.student_name)
        studentSurnameInput = findViewById(R.id.student_surname)
        studentAgeInput = findViewById(R.id.student_age)
        studentGroupInput = findViewById(R.id.student_group)
        studentList = findViewById(R.id.student_list)

        findViewById<Button>(R.id.create_group_button).setOnClickListener {
            val groupName = groupNameInput.text.toString()
            lifecycleScope.launch {
                db.groupDao().insertGroup(Group(name = groupName))
            }
        }

        findViewById<Button>(R.id.add_student_button).setOnClickListener {
            val name = studentNameInput.text.toString()
            val surname = studentSurnameInput.text.toString()
            val age = studentAgeInput.text.toString().toInt()
            val groupId = studentGroupInput.text.toString().toInt()
            lifecycleScope.launch {
                db.studentDao().insertStudent(Student(name = name, surname = surname, age = age, groupId = groupId))
            }
        }

        findViewById<Button>(R.id.show_students_button).setOnClickListener {
            lifecycleScope.launch {
                val students = db.studentDao().getAllStudents()
                runOnUiThread {
                    studentList.text = students.joinToString("\n") { "${it.name} ${it.surname}, Age: ${it.age}, Group: ${it.groupId}" }
                }
            }
        }
    }
}
