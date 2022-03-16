package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class forthCoachActivity : AppCompatActivity() {

    lateinit var coachBack3 : ImageView
    lateinit var coachClose : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forth_coach)

        coachClose = findViewById(R.id.coachClose)
        coachBack3 = findViewById(R.id.coachBack3)

        coachClose.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
        coachBack3.setOnClickListener {
            val intent = Intent(this, thirdCoachActivity::class.java)
            startActivity(intent)
        }
    }
}