package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class star_help : AppCompatActivity() {

    lateinit var sh_back : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_help)

        sh_back = findViewById(R.id.sh_back)

        sh_back.setOnClickListener{
            var intent = Intent(this, star1::class.java)
            startActivity(intent)
        }
    }
}