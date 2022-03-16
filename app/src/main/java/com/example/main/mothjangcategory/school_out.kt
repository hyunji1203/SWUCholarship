package com.example.main.mothjangcategory

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.*
import com.example.main.todo.MainActivity
import kotlinx.android.synthetic.main.activity_monthjang.*
import kotlinx.android.synthetic.main.main_drawer_header.*
import java.util.*

class school_out : AppCompatActivity() {

    //sql 데이터 베이스를 쓰기위해 변수 선언
    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    //메뉴바를 위한 변수 선언
    lateinit var homeBtn: ImageButton
    lateinit var infoBtn: ImageButton
    lateinit var ringBtn: ImageButton
    lateinit var myBtn: ImageButton
    lateinit var starBtn: ImageButton

    //드로어 여는 버튼
    lateinit var btnOpen : ImageButton

    lateinit var category_back2 : ImageView

    //리스트 선언
    var profileList2 =  ArrayList<profiles2>();

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_out)

        //메뉴 변수와 아이템 연결
        homeBtn = findViewById(R.id.homeBtn5)
        infoBtn = findViewById(R.id.infoBtn5)
        myBtn = findViewById(R.id.myBtn5)
        ringBtn = findViewById(R.id.ringBtn5)
        starBtn = findViewById(R.id.starBtn5)
        btnOpen = findViewById(R.id.btnOpen)

        category_back2 = findViewById(R.id.category_back2)

        //디데이 계산을 위한 변수 선언
        var year = 0
        var month = 0
        var day = 0
        lateinit var last : String

        var mon = intent.getStringExtra("month")

        //햄버거 버튼으로 드로어 열기
        btnOpen.setOnClickListener {
            email.setText(MySharedPreferences.getUserId(this))
            main_drawer_layout.openDrawer(GravityCompat.START)
        }

        //드로어 메뉴 선택 시, 페이지 전환
        menu01.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.swu.ac.kr/www/noticeb.html"))
            startActivity(intent)
        }

        menu02.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.swu.ac.kr/www/encoub.html"))
            startActivity(intent)
        }

        menu03.setOnClickListener {
            var intent = Intent(this, firstCoachActivity::class.java)
            startActivity(intent)
        }


        dbManager = DBManager(this, "august_day", null, 1)
        sqlitedb = dbManager.readableDatabase

        lateinit var cursor : Cursor

        //데이터베이스에서 type이 '교외'인 데이터를 cursor에 담음
        var a = "교외"
        cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE type = '"+a+"';", null)

        //while문을 돌며 필요한 데이터들을 가져
        while (cursor.moveToNext()){
            var first = cursor.getString((cursor.getColumnIndex("name")))
            var second = cursor.getString((cursor.getColumnIndex("period")))
            var third = cursor.getString((cursor.getColumnIndex("explain")))
            var forth = cursor.getString((cursor.getColumnIndex("type")))
            var bool = cursor.getInt((cursor.getColumnIndex("bool")))
            var star = cursor.getInt((cursor.getColumnIndex("star")))
            var fifth = cursor.getInt((cursor.getColumnIndex("typenum")))

            if (bool == 0 && star == 0){
                profileList2.add(profiles2(first,second,third,forth,R.drawable.alarm_gray,R.drawable.star_gray, "",0,0,0, fifth))
            }
            if (bool == 0 && star == 1){
                profileList2.add(profiles2(first,second,third,forth,R.drawable.alarm_gray,R.drawable.star_y, "",0,0,1, fifth))
            }
            if (bool == 1 && star == 0){
                profileList2.add(profiles2(first,second,third,forth,R.drawable.alarm_y,R.drawable.star_gray, "",0,1,0,fifth))
            }
            if(bool == 1 && star == 1) {
                profileList2.add(profiles2(first,second,third,forth,R.drawable.alarm_y,R.drawable.star_y, "",0,1,1,fifth))
            }
        }

        var x = 0
        lateinit var cursor2 : Cursor

        //디데이를 계산하기 위해 데이터베이스에서 필요한 값들을 불러옴
        var b = "교외"
        cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE type = '"+a+"';", null)

        while (cursor2.moveToNext()){
            year = cursor2.getInt((cursor.getColumnIndex("year")))
            month = cursor2.getInt((cursor.getColumnIndex("month")))
            day = cursor2.getInt((cursor.getColumnIndex("day")))

            //디데이 구하는 코드
            val onlyDate = fewDay(year, month, day)
            val lastDate = onlyDate - 31

            if (lastDate.toInt() == 0) {
                last = "D-DAY"
            } else if (lastDate.toInt() < 0) {
                last = "신청 마감"
            } else {
                last = "D - $lastDate"
            }

            profileList2[x].day2 = last
            profileList2[x].value = lastDate.toInt()
            x++
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()
        //////////////////////////////////////////

        //profileList3 알람 눌린 순으로 정렬하기기
        profileList2.sortByDescending { it.bool }

        //어댑터를 이용하여 리사이클러뷰와 리스트를 합치는 코드
        rvs_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvs_profile.setHasFixedSize(true)

        rvs_profile.adapter = ProfileAdapter2(profileList2)

        //메뉴버튼 클릭 시 화면 전환
        homeBtn.setOnClickListener {
            var intent = Intent(this, MainActivity_home::class.java)
            startActivity(intent)
        }
        ringBtn.setOnClickListener {
            var intent = Intent(this, alarm::class.java)
            startActivity(intent)
        }
        starBtn.setOnClickListener {
            var intent = Intent(this, star1::class.java)
            startActivity(intent)
        }
        myBtn.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //뒤로가는 버튼을 눌렀을 때 그전의 페이지로 가게하는 버튼
        category_back2.setOnClickListener {
            finish()
        }
    }

    //디데이 구하는 함수
    @RequiresApi(Build.VERSION_CODES.O)
    fun fewDay(year: Int, month: Int, day: Int): Long {
        val lastDay = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }.timeInMillis

        val today = Calendar.getInstance().timeInMillis

        val fewDay = getIgnoreTimeDays(lastDay) - getIgnoreTimeDays(today)

        return fewDay / (24 * 60 * 60 * 1000)
    }

    fun getIgnoreTimeDays(time: Long): Long {
        return Calendar.getInstance().apply {
            timeInMillis = time

            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }
}