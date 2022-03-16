package com.example.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.main.databinding.ActivityGradeBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

// 회원가입 엑티비티
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var dbHelper: LoginDB = LoginDB(applicationContext, "MEMBER.db", null, 1)

        //회원 정보를 데이터베이스에 저장하는 부분
        registerNext.setOnClickListener {
            var email: String = registerEmail.getText().toString()
            var password: String = registerPassword.getText().toString()

            if (email.length == 0 || password.length == 0) {
                Toast.makeText(this, "email 혹은 password를 반드시 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                dbHelper.insert(email, password)
                val intent = Intent(this, MajorActivity::class.java)
                startActivity(intent)
            }
        }

        registerBefore.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }
}