package com.example.main
//알림관련페이지
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.mothjangcategory.nation
import com.example.main.mothjangcategory.school_in
import com.example.main.mothjangcategory.school_out
import com.example.main.todo.MainActivity
import kotlinx.android.synthetic.main.activity_main_home.*
import kotlinx.android.synthetic.main.activity_monthjang.*
import kotlinx.android.synthetic.main.activity_monthjang.main_drawer_layout
import kotlinx.android.synthetic.main.activity_monthjang.main_toolbar
import kotlinx.android.synthetic.main.activity_monthjang.menu01
import kotlinx.android.synthetic.main.activity_monthjang.menu02
import kotlinx.android.synthetic.main.activity_monthjang.menu03
import kotlinx.android.synthetic.main.main_drawer_header.*
import java.util.*
import kotlinx.android.synthetic.main.activity_main_home.menu04 as menu041

class monthjang : AppCompatActivity() {
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

    lateinit var monthjang_back : ImageView
    lateinit var monthjang_name : TextView

    //리스트 선언
    var profileList2 =  ArrayList<profiles2>();

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthjang)

        //main 툴바 선언
        setSupportActionBar(findViewById<Toolbar>(R.id.main_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        //메뉴 변수와 아이템 연결
        homeBtn = findViewById(R.id.homeBtn5)
        infoBtn = findViewById(R.id.infoBtn5)
        myBtn = findViewById(R.id.myBtn5)
        ringBtn = findViewById(R.id.ringBtn5)
        starBtn = findViewById(R.id.starBtn5)
        btnOpen = findViewById(R.id.btnOpen)


        monthjang_back = findViewById(R.id.monthjang_back)
        monthjang_name = findViewById(R.id.monthjang_name)


        //커스텀 툴바를 액션바로 바꿔주는 코드
        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

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

        menu04.setOnClickListener {
            MySharedPreferences.clearUser(this)
            var intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //디데이 계산을 위한 변수 선언
        var year = 0
        var month = 0
        var day = 0
        lateinit var last : String

        //MainActivity_home 페이지에서 월별 글자를 클릭시 그 글자의 값을 가져옴
        var mon = intent.getStringExtra("month")

        dbManager = DBManager(this, "august_day", null, 1)
        sqlitedb = dbManager.readableDatabase

        //가져온 글자에 따라 보여지는 월별 장학금 리스트가 달라지게 구성함
        lateinit var cursor : Cursor
        if (mon == "8월"){
            var a = 8
            cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
            monthjang_name.text = "8월의 장학금"
        } else if (mon == "9월"){
            var a = 9
            cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
            monthjang_name.text = "9월의 장학금"
        } else if (mon == "10월"){
            var a = 10
            cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
            monthjang_name.text = "10월의 장학금"
        } else if (mon == "11월"){
            var a = 11
            cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
            monthjang_name.text = "11월의 장학금"
        } else if (mon == "12월"){
            var a = 12
            cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
            monthjang_name.text = "12월의 장학금"
        } else if (mon == "그 외"){
            var a = 0
            cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
            monthjang_name.text = "그 외 장학금"
        } else{
            var a = 11
            cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
            monthjang_name.text = "11월의 장학금"
        }
        //리스트를 나타내기 위해 각각의 필드 값을 가져온 후 상황에 맞게 설명 후 리스트에 넣는 작업
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

        //선택된 월에 따라 불러오는 cursor가 달라지게 설정
        if (mon == "8월"){
            var a = 8
            cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
        }
        else if (mon == "9월"){
            var a = 9
            cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
        }
        else if (mon == "10월") {
            var a = 10
            cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
        }
        else if (mon == "11월") {
            var a = 11
            cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
        }
        else if (mon == "12월") {
            var a = 12
            cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
        }
        else if (mon == "그 외") {
            var a = 0
            cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
        }
        else {
            var a = 11
            cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)
        }

        //기간 미정인 장학금일 시 디데이 부분에 기장 미정이 뜨도록 설정
        if ( mon == "그 외"){
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
                    last = "기간 미정"
                } else {
                    last = "D - $lastDate"
                }

                profileList2[x].day2 = last
                profileList2[x].value = lastDate.toInt()
                x++
            }
        }
        else {  //그외의 장학금은 평소처럼 디데이가 뜨게 설정
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
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()
        //////////////////////////////////////////

        //profileList3 알람 눌린 순으로 정렬하기기
        profileList2.sortByDescending { it.bool }

        //리사이클러뷰 연결 코드
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
        monthjang_back.setOnClickListener {
            finish()
        }


    }

    //메뉴를 인식시키기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    //메뉴 선택시 각각 해당하는 행동을 하게 해주는 코드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.school_in -> {
                var intent = Intent(this, school_in::class.java)
                startActivity(intent)
                return true
            }
            R.id.school_out -> {
                var intent = Intent(this, school_out::class.java)
                startActivity(intent)
                return true
            }
            R.id.nation -> {
                var intent = Intent(this, nation::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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