package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class thirdCoachActivity : AppCompatActivity() {

    lateinit var coachNext3 : ImageView
    lateinit var coachBack2 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_coach)

        coachNext3 = findViewById(R.id.coachNext3)
        coachBack2 = findViewById(R.id.coachBack2)

        coachNext3.setOnClickListener {
            val intent = Intent(this, forthCoachActivity::class.java)
            startActivity(intent)
        }
        coachBack2.setOnClickListener {
            val intent = Intent(this, secondCoachActivity::class.java)
            startActivity(intent)
        }
    }
}