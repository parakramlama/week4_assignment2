package com.parakram.week4_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.parakram.week4_assignment2.model.Student
import java.util.*
import kotlin.collections.ArrayList

class Result : AppCompatActivity() {
    private var students = ArrayList<Student>()

    private lateinit var firstPositionText : TextView
    private lateinit var secondPositionText : TextView
    private lateinit var thirdPositionText : TextView

    private lateinit var androidMarksFirstText : TextView
    private lateinit var androidMarksSecondText : TextView
    private lateinit var androidMarksThirdText : TextView

    private lateinit var homeBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        firstPositionText = findViewById(R.id.tvPositionFirst)
        secondPositionText = findViewById(R.id.tvPositionSecond)
        thirdPositionText = findViewById(R.id.tvPositionThird)

        androidMarksFirstText = findViewById(R.id.tvFirstAndroidMarks)
        androidMarksSecondText = findViewById(R.id.tvSecondAndroidMarks)
        androidMarksThirdText = findViewById(R.id.tvThirdAndroidMarks)

        homeBtn = findViewById(R.id.btnHome)

        if (intent.extras != null) {
            students = intent.getSerializableExtra("students") as ArrayList<Student>
        }

        calcResult()
        calcAndroidResult()
        homeBtnEvent()
    }

    private fun calcResult() {
        val groupedPercentages = students.groupBy { it.percentage() }.toSortedMap(reverseOrder())
        val keys = groupedPercentages.keys.toTypedArray()
        val percentLambda = fun(student : Student) : String {
            return student.studentName + " - " + String.format("%.2f", student.percentage) + "%"
        }

        firstPositionText.text = getText(groupedPercentages, keys, 0, percentLambda)
        secondPositionText.text = getText(groupedPercentages, keys, 1, percentLambda)
        thirdPositionText.text = getText(groupedPercentages, keys, 2, percentLambda)
    }

    private fun calcAndroidResult() {
        val groupedAndroid = students.groupBy{ it.androidMarks.toDouble() }.toSortedMap(reverseOrder())
        val keys = groupedAndroid.keys.toTypedArray()
        val androidLambda = fun(student : Student) : String {
            return student.studentName + " - " + student.androidMarks
        }

        androidMarksFirstText.text = getText(groupedAndroid, keys, 0, androidLambda)
        androidMarksSecondText.text = getText(groupedAndroid, keys, 1, androidLambda)
        androidMarksThirdText.text = getText(groupedAndroid, keys, 2, androidLambda)
    }

    private fun getText(sortedData : SortedMap<Double, List<Student>>, keys: Array<Double>, index: Int, getInfo : (Student) -> String ) : String {
        val studentList = if(keys.getOrNull(index) != null) sortedData[keys[index]] else emptyList()
        var studentListText = ""
        if (studentList != null) {
            for (student in studentList) {
                studentListText = studentListText + getInfo(student) + ", "
            }
        }
        return studentListText.trimEnd(' ', ',')
    }

    private fun homeBtnEvent() {
        homeBtn.setOnClickListener {
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
        }
    }
}




//package com.example.week4assignment2marks
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import com.example.week4assignment2marks.model.Student
//import java.util.*
//import kotlin.collections.ArrayList

