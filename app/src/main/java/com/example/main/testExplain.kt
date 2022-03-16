package com.example.main

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.main.databinding.ActivityTestExplainBinding
import com.example.main.search.Search
import com.example.main.search.searchMainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main_home.*
import kotlinx.android.synthetic.main.activity_star1.*
import kotlinx.android.synthetic.main.activity_star1.menu01
import kotlinx.android.synthetic.main.activity_star1.menu02
import kotlinx.android.synthetic.main.activity_star1.menu03
import kotlinx.android.synthetic.main.activity_star1.menu04
import kotlinx.android.synthetic.main.activity_test_explain.main_toolbar
import kotlinx.android.synthetic.main.main_drawer_header.*

//상세 설명 페이지
class testExplain : AppCompatActivity() {

    lateinit var a : String

    lateinit var back : ImageView

    lateinit var dbManager : DBManager_ex
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var explain_name : TextView

    lateinit var ex_alarm : ImageView
    lateinit var ex_star : ImageView

    //드로어 여는 버튼
    lateinit var btnOpen : ImageButton

    //상세설명에 적힐 데이터가 담길 변수 선언
    var x : String = ""
    var y : String = ""
    var z : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_explain)

        btnOpen = findViewById(R.id.btnOpen)

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

        var back = findViewById<ImageView>(R.id.back)
        explain_name = findViewById<TextView>(R.id.explain_name)

        ex_alarm = findViewById(R.id.ex_alarm)
        ex_star = findViewById(R.id.ex_star)

        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter=ViewPagerAdapter(supportFragmentManager,lifecycle)

        //커스텀 툴바를 액션바로 바꿔주는 코드
        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        viewPager2.adapter=adapter

        //리스트가 클릭 됐을 때 전달해온 이름값을 받음
        //var name = intent.getStringExtra("name")

        var a = ""
        var b = ""
        var c = 0
        var d = 0

        var bool = 0
        var star = 0

        //intent를 사용하여 알림 설정을 할 장학금 받아오기
        if (intent.hasExtra("examKey")) {
            var exam = intent.getParcelableExtra<Exam>("examKey")
            // exam변수를 초기화 할 때 자료형이 정해지지 않아서 getParcelableExtra 뒤에 <>가 생기고, 이곳에 자료형을 입력한다. */

            //exam에서 받은 값들을 변수에 저장시킴
            if (exam != null) {
                a = exam.name
                b = exam.day
                c = exam.value
                d = exam.typenum
            }
        }

        //그 값을 이름 칸에 적음
        explain_name.text = a

        dbManager = DBManager_ex(this, "detail_ex", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM detail_ex WHERE name = '"+a+"';", null)

        //상세설명이 담긴 데이터베이스에서 해당 장학금 데이터를 가져옴
        while (cursor.moveToNext()) {
            x = cursor.getString((cursor.getColumnIndex("detail")))
            y = cursor.getString((cursor.getColumnIndex("qualify")))
            z = cursor.getString((cursor.getColumnIndex("original")))
        }

        //탭 레이아웃 이름 설정
        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="상세요강"
                }
                1->{
                    tab.text="신청조건"
                }
                2->{
                    tab.text="원문공고"
                }
            }
        }.attach()

        //뒤로가기 버튼
        back.setOnClickListener {
            finish()
        }

        sqlitedb.close()
        dbManager.close()

        var dbManager_original = DBManager(this, "august_day", null, 1)
        sqlitedb = dbManager_original.readableDatabase

        var cursor_original : Cursor
        cursor_original = sqlitedb.rawQuery("SELECT * FROM august_day WHERE name = '"+a+"';", null)

        //리스트를 나타내기 위해 각각의 필드 값을 가져온 후 상황에 맞게 설명 후 리스트에 넣는 작업
        while (cursor_original.moveToNext()){
            bool = cursor_original.getInt((cursor_original.getColumnIndex("bool")))
            star = cursor_original.getInt((cursor_original.getColumnIndex("star")))

            if (bool == 0 && star == 0){
                ex_alarm.setImageResource(R.drawable.alarm_gray)
                ex_star.setImageResource(R.drawable.star_gray)

                ex_alarm.setOnClickListener{

                    //알림 설정 데이터 베이스에 리스트 추가함
                    sqlitedb.execSQL("UPDATE august_day SET bool = '"+1+"' WHERE name = '"+a+"';")

                    var myExam = Exam(a, b, c, d)

                    var intent = Intent(this, alarm::class.java)
                    intent.putExtra("examKey", myExam)
                    startActivity(intent)
                }

                ex_star.setOnClickListener {

                    var myExam = Exam(a, b, c, d)

                    //즐겨찾기 관리 데이터베이스 리스트에 추가함
                    sqlitedb.execSQL("UPDATE august_day SET star = '"+1+"' WHERE name = '"+a+"';")

                    //즐겨찾기 창으로 이동
                    var intent2 = Intent(this, star1::class.java)
                    intent2.putExtra("examKey", myExam)
                    startActivity(intent2)
                }
            }
            if (bool == 0 && star == 1){
                ex_alarm.setImageResource(R.drawable.alarm_gray)
                ex_star.setImageResource(R.drawable.star_y)

                ex_alarm.setOnClickListener{

                    //알림 설정 데이터 베이스에 리스트 추가함
                    sqlitedb.execSQL("UPDATE august_day SET bool = '"+1+"' WHERE name = '"+a+"';")

                    var myExam = Exam(a, b, c, d)

                    var intent = Intent(this, alarm::class.java)
                    intent.putExtra("examKey", myExam)
                    startActivity(intent)
                }
                ex_star.setOnClickListener {
                    ex_star.setImageResource(R.drawable.star_gray)

                    //즐겨찾기 관리 데이터베이스 리스트에 추가함
                    sqlitedb.execSQL("UPDATE august_day SET star = '"+0+"' WHERE name = '"+a+"';")

                    var dbManager = DBManager_star(this, "star_list", null, 1)
                    sqlitedb = dbManager.writableDatabase

                    //즐겨찾기 리스트 데이터 베이스에서 이름을 제거함
                    sqlitedb.execSQL("DELETE FROM star_list WHERE name = '"+ a +"';")

                    sqlitedb.close()
                    dbManager.close()

                var intent = Intent(this, star1::class.java)
                startActivity(intent)
                }
            }
            if (bool == 1 && star == 0){
                ex_alarm.setImageResource(R.drawable.alarm_y)
                ex_star.setImageResource(R.drawable.star_gray)

                ex_alarm.setOnClickListener{
                    ex_alarm.setImageResource(R.drawable.alarm_gray)

                    //알림 설정 데이터 베이스에 리스트 추가함
                    sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+a+"';")

                    var dbManager = DBManager_alarm(this, "alarm_list", null, 1)
                    sqlitedb = dbManager.writableDatabase

                    //알림 설정 데이터 베이스에서 제거함
                    sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ a +"';")

                    var dbManager_cost = DBManager_cost(this, "cost_list", null, 1)
                    sqlitedb = dbManager_cost.writableDatabase

                    //등록금 계산기 데이터 베이스에서 제거함
                    sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ a +"';")

                    sqlitedb.close()
                    dbManager.close()

                var intent = Intent(this, alarm::class.java)
                startActivity(intent)
                }
                ex_star.setOnClickListener {

                    var myExam = Exam(a, b, c, d)

                    //즐겨찾기 관리 데이터베이스 리스트에 추가함
                    sqlitedb.execSQL("UPDATE august_day SET star = '"+1+"' WHERE name = '"+a+"';")

                    //즐겨찾기 창으로 이동
                    var intent2 = Intent(this, star1::class.java)
                    intent2.putExtra("examKey", myExam)
                    startActivity(intent2)
                }

            }
            if(bool == 1 && star == 1) {
                ex_alarm.setImageResource(R.drawable.alarm_y)
                ex_star.setImageResource(R.drawable.star_y)

                ex_alarm.setOnClickListener{
                    ex_alarm.setImageResource(R.drawable.alarm_gray)

                    //알림 설정 데이터 베이스에 리스트 추가함
                    sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+a+"';")

                    var dbManager = DBManager_alarm(this, "alarm_list", null, 1)
                    sqlitedb = dbManager.writableDatabase

                    //알림 설정 데이터 베이스에서 제거함
                    sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ a +"';")

                    var dbManager_cost = DBManager_cost(this, "cost_list", null, 1)
                    sqlitedb = dbManager_cost.writableDatabase

                    //등록금 계산기 데이터 베이스에서 제거함
                    sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ a +"';")

                    sqlitedb.close()
                    dbManager.close()

                var intent = Intent(this, alarm::class.java)
                startActivity(intent)
                }
                ex_star.setOnClickListener {
                    ex_star.setImageResource(R.drawable.star_gray)

                    //즐겨찾기 관리 데이터베이스 리스트에 추가함
                    sqlitedb.execSQL("UPDATE august_day SET star = '"+0+"' WHERE name = '"+a+"';")

                    var dbManager = DBManager_star(this, "star_list", null, 1)
                    sqlitedb = dbManager.writableDatabase

                    //즐겨찾기 리스트 데이터 베이스에서 이름을 제거함
                    sqlitedb.execSQL("DELETE FROM star_list WHERE name = '"+ a +"';")

                    sqlitedb.close()
                    dbManager.close()

                var intent = Intent(this, star1::class.java)
                startActivity(intent)
                }
            }
        }
    }

    //메뉴를 인식시키기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //메뉴 선택시 각각 해당하는 행동을 하게 해주는 코드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val main_drawer_layout = findViewById<DrawerLayout>(R.id.main_drawer_layout)
        when(item?.itemId) {
            R.id.action_search -> {
                var intent = Intent(this, Search::class.java)
                startActivity(intent)
                return true
            }
            R.id.login -> {
                var intent = Intent(this, loginActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}