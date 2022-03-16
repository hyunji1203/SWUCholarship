package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class secondCoachActivity : AppCompatActivity() {

    lateinit var coachNext2 : ImageView
    lateinit var coachBack1 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_coach)

        coachNext2 = findViewById(R.id.coachNext2)
        coachBack1 = findViewById(R.id.coachBack1)

        coachNext2.setOnClickListener {
            val intent = Intent(this, thirdCoachActivity::class.java)
            startActivity(intent)
        }
        coachBack1.setOnClickListener {
            val intent = Intent(this, firstCoachActivity::class.java)
            startActivity(intent)
        }
    }
}