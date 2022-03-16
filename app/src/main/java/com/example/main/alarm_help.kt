package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class alarm_help : AppCompatActivity() {

    lateinit var ah_back : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_help)

        ah_back = findViewById(R.id.ah_back)

        ah_back.setOnClickListener{
            var intent = Intent(this, alarm::class.java)
            startActivity(intent)
        }
    }
}