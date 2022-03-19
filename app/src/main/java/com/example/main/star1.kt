package com.example.main

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.main.search.Search
import com.example.main.search.searchMainActivity
import com.example.main.todo.MainActivity
import kotlinx.android.synthetic.main.activity_alarm.*
import kotlinx.android.synthetic.main.activity_alarm.rvs_profile
import kotlinx.android.synthetic.main.activity_main_home.*
import kotlinx.android.synthetic.main.activity_main_home.menu01
import kotlinx.android.synthetic.main.activity_main_home.menu02
import kotlinx.android.synthetic.main.activity_main_home.menu03
import kotlinx.android.synthetic.main.activity_star1.*
import kotlinx.android.synthetic.main.main_drawer_header.*
import kotlinx.android.synthetic.main.activity_alarm.menu04 as menu041
import kotlinx.android.synthetic.main.activity_star1.menu04 as menu041

class star1 : AppCompatActivity() {

    //데이터 베이스 사용을 위해 변수 선언
    lateinit var dbManager : DBManager_star
    lateinit var sqlitedb : SQLiteDatabase

    //메뉴 버튼 선언
    lateinit var homeBtn: ImageButton
    lateinit var infoBtn: ImageButton
    lateinit var ringBtn: ImageButton
    lateinit var myBtn: ImageButton
    lateinit var starBtn: ImageButton

    //드로어 여는 버튼
    lateinit var btnOpen : ImageButton

    //해시태그 선언
    lateinit var a_hash1 : ImageButton
    lateinit var a_hash2 : ImageButton
    lateinit var a_hash3 : ImageButton
    lateinit var a_hash4 : ImageButton
    lateinit var a_hash5 : ImageButton
    lateinit var a_hash6 : ImageButton

    lateinit var help_star : ImageButton


    //리스트 선언
    var profileList4 =  ArrayList<profiles4>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star1)

        setSupportActionBar(findViewById<Toolbar>(R.id.main_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        //선언 변수 메뉴 버튼과 연결시키기
        homeBtn = findViewById(R.id.homeBtn5)
        infoBtn = findViewById(R.id.infoBtn5)
        myBtn = findViewById(R.id.myBtn5)
        ringBtn = findViewById(R.id.ringBtn5)
        starBtn = findViewById(R.id.starBtn5)
        btnOpen = findViewById(R.id.btnOpen)


        //선언 변수 해시태그와 연결시키기
        a_hash1 = findViewById(R.id.m_hash1)
        a_hash2 = findViewById(R.id.m_hash2)
        a_hash3 = findViewById(R.id.m_hash3)
        a_hash4 = findViewById(R.id.m_hash4)
        a_hash5 = findViewById(R.id.m_hash5)
        a_hash6 = findViewById(R.id.m_hash6)

        //도움말 버튼 선언
        help_star = findViewById(R.id.help_star)

        //커스텀 툴바를 액션바로 바꿔주는 코드
        //setSupportActionBar(main_toolbar)
        //supportActionBar?.setDisplayShowTitleEnabled(false)

        //햄버거 버튼으로 드로어 열기
        btnOpen.setOnClickListener {
            email.setText(MySharedPreferences.getUserId(this))
            main_drawer_layout_star1.openDrawer(GravityCompat.START)
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
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //즐겨찾기 관리 데이터베이스를 엶
        dbManager = DBManager_star(this, "star_list", null, 1)

        var a = ""
        var b = 0

        //intent를 사용하여 알림 설정을 할 장학금 받아오기
        if (intent.hasExtra("examKey")) {
            var exam = intent.getParcelableExtra<Exam>("examKey")
            // exam변수를 초기화 할 때 자료형이 정해지지 않아서 getParcelableExtra 뒤에 <>가 생기고, 이곳에 자료형을 입력한다. */

            //monthjang 페이지에서 즐겨찾기가 추가될때 받은 값을 통해 즐겨찾기 데이터베이스에 해당 장학금을 추가함
            if (exam != null) {
                a = exam.name
                b = exam.typenum
            }
            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO star_list VALUES ('" +a+"', '"+b+"')")
        }

        dbManager = DBManager_star(this, "star_list", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor = sqlitedb.rawQuery("SELECT * FROM star_list", null)

        //즐겨찾기 데이터베이스에 있는 값들을 리스트에 추가함
        while (cursor.moveToNext()){
            var first = cursor.getString((cursor.getColumnIndex("name")))
            var second = cursor.getInt((cursor.getColumnIndex("typenum")))

            profileList4.add(profiles4(first, R.drawable.star_y, second))
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        //Adapter를 활용하여 list와 XML 이어주기
        rvs_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvs_profile.setHasFixedSize(true)

        rvs_profile.adapter = ProfileAdapter4(profileList4)

        //도움말 클린 버튼
        help_star.setOnClickListener {
            var intent = Intent(this, star_help::class.java)
            startActivity(intent)
        }

        //메뉴 클릭 버튼
        homeBtn.setOnClickListener {
            var intent = Intent(this, MainActivity_home::class.java)
            startActivity(intent)
        }
        infoBtn.setOnClickListener {
            var intent = Intent(this, monthjang::class.java)
            startActivity(intent)
        }
        ringBtn.setOnClickListener {
            var intent = Intent(this, alarm::class.java)
            startActivity(intent)
        }
        myBtn.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 해시태그 클릭 시 화면 이동
        a_hash1.setOnClickListener {
            var intent = Intent(this, hash_three::class.java)
            startActivity(intent)
        }
        a_hash2.setOnClickListener {
            var intent = Intent(this, hash2::class.java)
            startActivity(intent)
        }
        a_hash3.setOnClickListener {
            var intent = Intent(this, hash3::class.java)
            startActivity(intent)
        }
        a_hash4.setOnClickListener {
            var intent = Intent(this, hash4::class.java)
            startActivity(intent)
        }
        a_hash5.setOnClickListener {
            var intent = Intent(this, hash5::class.java)
            startActivity(intent)
        }
        a_hash6.setOnClickListener {
            var intent = Intent(this, hash6::class.java)
            startActivity(intent)
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