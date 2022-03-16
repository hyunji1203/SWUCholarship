package com.example.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.main.databinding.ActivityGradeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main_home.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.main_drawer_header.*
import kotlinx.android.synthetic.main.main_drawer_header.view.*

class loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var dbHelper: LoginDB = LoginDB(applicationContext, "MEMBER.db", null, 1)

        loginBtn.setOnClickListener {
            if (dbHelper.getResult1(loginEmail.getText().toString(), loginPassword.getText().toString()) == true) {
                Toast.makeText(this,"로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity_home::class.java)
                startActivity(intent)

                email.text = loginEmail.getText().toString()
            } else {
                Toast.makeText(this,"로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

        //회원가입을 누르면 회원가입 페이지로 넘어감
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
        if(MySharedPreferences.getUserId(this).isNullOrBlank()
            || MySharedPreferences.getUserPass(this).isNullOrBlank()) {
            Login()
        }
        else { // SharedPreferences 안에 값이 저장되어 있을 때 -> MainActivity로 이동
            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity_home::class.java)
            startActivity(intent)

            finish()
        }
    }

    fun Login() {

        loginBtn.setOnClickListener {

            if(loginEmail.text.isNullOrBlank() || loginPassword.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                MySharedPreferences.setUserId(this, loginEmail.text.toString())
                MySharedPreferences.setUserPass(this, loginPassword.text.toString())
                Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 로그인 되었습니다.", Toast.LENGTH_SHORT).show()

                    var intent = Intent(this, MainActivity_home::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}
