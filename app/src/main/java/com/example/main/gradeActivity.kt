package com.example.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_grade.*

// 학년 선택 엑티비티
class gradeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        registerFinish.setOnClickListener {
            // 회원가입 성공 토스트 메세지 띄우기
            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

            //로그인 화면 으로 이동
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

        gradeBefore.setOnClickListener {
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
        }
    }
}