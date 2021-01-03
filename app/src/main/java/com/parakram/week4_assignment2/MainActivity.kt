package com.parakram.week4_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.parakram.week4_assignment2.model.Student

class MainActivity : AppCompatActivity() {
    private lateinit var studentID : EditText
    private lateinit var studentName : EditText
    private lateinit var androidMarks : EditText
    private lateinit var iotMarks : EditText
    private lateinit var apiMarks : EditText
    private lateinit var addToListBtn : Button

    private var students = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentID = findViewById(R.id.etStudentId)
        studentName = findViewById(R.id.etStudentName)
        androidMarks = findViewById(R.id.etAndroidMarks)
        iotMarks = findViewById(R.id.etIotMarks)
        apiMarks = findViewById(R.id.etApiMarks)
        addToListBtn = findViewById(R.id.btnAdd)

        attachEventLister()
    }

    private fun attachEventLister() {
        addToListBtn.setOnClickListener {
            if (students.size >= 3) {
                return@setOnClickListener
            }
            students.add(Student(
                studentID.text.toString(),
                studentName.text.toString(),
                androidMarks.text.toString().toInt(),
                iotMarks.text.toString().toInt(),
                apiMarks.text.toString().toInt()
            ))
            resetForm()
            if (students.size == 3) {
                displayResult()
            }
        }
    }

    private fun resetForm() {
        studentID.setText("")
        studentName.setText("")
        androidMarks.setText("")
        iotMarks.setText("")
        apiMarks.setText("")
    }

    private fun displayResult() {
        val resultActivity = Intent(this, Result::class.java)
        resultActivity.putExtra("students", students)
        startActivity(resultActivity)
    }
}

