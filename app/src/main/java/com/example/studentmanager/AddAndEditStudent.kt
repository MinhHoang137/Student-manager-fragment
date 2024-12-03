package com.example.studentmanager

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddAndEditStudent: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_student)
        val editFullName = findViewById<EditText>(R.id.edit_fullname)
        val editStudentId = findViewById<EditText>(R.id.edit_student_id)
        val buttonCancel = findViewById<Button>(R.id.button_cancel)
        val buttonOk = findViewById<Button>(R.id.button_ok)
        editFullName.setText(intent.getStringExtra(STUDENT_NAME))
        editStudentId.setText(intent.getStringExtra(STUDENT_ID))
        setResult(Activity.RESULT_CANCELED)
        buttonOk.setOnClickListener {
            val canAddAndEditStudent = !editFullName.text.isNullOrEmpty() || !editStudentId.text.isNullOrEmpty()
            if (canAddAndEditStudent){
                val intent = intent
                intent.putExtra(STUDENT_NAME, editFullName.text.toString())
                intent.putExtra(STUDENT_ID, editStudentId.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else{
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
    public companion object{
        const val STUDENT_NAME = "student_name"
        const val STUDENT_ID = "student_id"
        fun mark(textView: TextView, cp: String, key: String) {
            val keyExpanded = key.repeat((cp.length / key.length) + 1).substring(0, cp.length)
            val decodedText = buildString {
                for (i in cp.indices) {
                    val decodedChar = if (cp[i].isDigit() && keyExpanded[i].isDigit()) {
                        ((cp[i] - '0' - (keyExpanded[i] - '0') + 10) % 10 + '0'.code).toChar()
                    } else {
                        cp[i]
                    }
                    append(decodedChar)
                }
            }
            textView.text = buildString {
                append(textView.text.toString())
                append(decodedText)
            }
        }

    }
}