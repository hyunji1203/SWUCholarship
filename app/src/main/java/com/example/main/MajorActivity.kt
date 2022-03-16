package com.example.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_major.*

// 전공 선택 엑티비티
class MajorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)

        // 다음 버튼을 클릭하면 학년 선택 페이지로 넘어감
        majorNext.setOnClickListener {
            val intent = Intent(this, gradeActivity::class.java)

            if (rb1.isChecked) {
                intent.putExtra("first_Money", 3490000)
                startActivity(intent)
            } else if (rb2.isChecked) {
                intent.putExtra("first_Money", 3490000)
                startActivity(intent)
            } else if (rb3.isChecked) {
                intent.putExtra("first_Money", 3490000)
                startActivity(intent)
            } else if (rb4.isChecked) {
                intent.putExtra("first_Money", 3490000)
                startActivity(intent)
            } else if (rb5.isChecked) {
                intent.putExtra("first_Money", 4189000)
                startActivity(intent)
            } else if (rb6.isChecked) {
                intent.putExtra("first_Money", 4189000)
                startActivity(intent)
            } else if (rb7.isChecked) {
                intent.putExtra("first_Money", 4189000)
                startActivity(intent)
            } else if (rb8.isChecked) {
                intent.putExtra("first_Money", 4189000)
                startActivity(intent)
            } else if (rb9.isChecked) {
                intent.putExtra("first_Money", 4886000)
                startActivity(intent)
            } else if (rb10.isChecked) {
                intent.putExtra("first_Money", 4886000)
                startActivity(intent)
            }
        }

        majorBefore.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
