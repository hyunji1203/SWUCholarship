package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.main.R

class firstCoachActivity : AppCompatActivity() {

    lateinit var coachNext1 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_coach)

        coachNext1 = findViewById(R.id.coachNext1)

        coachNext1.setOnClickListener {
            val intent = Intent(this, secondCoachActivity::class.java)
            startActivity(intent)
        }
    }
}