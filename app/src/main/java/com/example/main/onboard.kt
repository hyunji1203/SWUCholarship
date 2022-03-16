package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

// 앱에서 가장 먼저 시작하는 화면 - 팀 로고가 띄워저 있음
class onboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onbording)

        // onboard가 먼저 실행되고 MainActivity_home가 실행됨
        val i = Intent(this@onboard, firstCoachActivity::class.java)
        Handler().postDelayed({
            startActivity(i)
            finish()
        }, 1000)
    }
}