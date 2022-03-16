package com.example.main.search

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.main.*

class searchMainActivity : AppCompatActivity() {

    lateinit var Word: EditText

    //해시태그 선언
    lateinit var s_hash1 : ImageButton
    lateinit var s_hash2 : ImageButton
    lateinit var s_hash3 : ImageButton
    lateinit var s_hash4 : ImageButton
    lateinit var s_hash5 : ImageButton
    lateinit var s_hash6 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_main)

        Word = findViewById(R.id.searchWord)

        //선언 변수 해시태그와 연결시키기
        s_hash1 = findViewById(R.id.s_hash1)
        s_hash2 = findViewById(R.id.s_hash2)
        s_hash3 = findViewById(R.id.s_hash3)
        s_hash4 = findViewById(R.id.s_hash4)
        s_hash5 = findViewById(R.id.s_hash5)
        s_hash6 = findViewById(R.id.s_hash6)

        Word.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        // 해시태그 클릭 시 화면 이동
        s_hash1.setOnClickListener {
            var intent = Intent(this, hash_three::class.java)
            startActivity(intent)
        }
        s_hash2.setOnClickListener {
            var intent = Intent(this, hash2::class.java)
            startActivity(intent)
        }
        s_hash3.setOnClickListener {
            var intent = Intent(this, hash3::class.java)
            startActivity(intent)
        }
        s_hash4.setOnClickListener {
            var intent = Intent(this, hash4::class.java)
            startActivity(intent)
        }
        s_hash5.setOnClickListener {
            var intent = Intent(this, hash5::class.java)
            startActivity(intent)
        }
        s_hash6.setOnClickListener {
            var intent = Intent(this, hash6::class.java)
            startActivity(intent)
        }
    }
}