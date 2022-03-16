package com.example.main.mothjangcategory

import android.content.Intent
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
import kotlinx.android.synthetic.main.frag1.*
import kotlinx.android.synthetic.main.main_drawer_header.*

class nation : AppCompatActivity() {

    lateinit var category_back1 : ImageView

    //메뉴바를 위한 변수 선언
    lateinit var homeBtn: ImageButton
    lateinit var infoBtn: ImageButton
    lateinit var ringBtn: ImageButton
    lateinit var myBtn: ImageButton
    lateinit var starBtn: ImageButton

    //드로어 여는 버튼
    lateinit var btnOpen : ImageButton

    var profileList = java.util.ArrayList<profiles>()
    lateinit var category_back3 : ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nation)

        category_back3 =findViewById(R.id.category_back3)

        //메뉴 변수와 아이템 연결
        homeBtn = findViewById(R.id.homeBtn5)
        infoBtn = findViewById(R.id.infoBtn5)
        myBtn = findViewById(R.id.myBtn5)
        ringBtn = findViewById(R.id.ringBtn5)
        starBtn = findViewById(R.id.starBtn5)
        btnOpen = findViewById(R.id.btnOpen)

        var year = 0
        var month = 0
        var day = 0
        lateinit var last : String

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


        //국가장학금 리스트 선
        profileList.add(profiles("국가근로장학금", "2021.09.01~2022.02.28", "안정적인 학업여건 조성과 취업역량 제고를 위한 장학금","국가","--" ,0,2))
        profileList.add(profiles("대학생 청소년교육지원장학금", "2학기","대학생들의 지식과 경험을 나누는 기회와 등록금 부담 감소", "국가","--",0, 2))
        profileList.add(profiles("국가우수장학금","2021.07.27~2021.08.26","우수 인재를 이공계로 적극 유도하여 국가 핵심 인재군으로 육성","국가","--", 0, 2))

        //ProfileAdapter와 리사이클러뷰를 연결시키는 코드
        rvs_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvs_profile.setHasFixedSize(true)

        rvs_profile.adapter = ProfileAdapter(profileList)

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

        //뒤로가기 버튼을 누르면 이전 페이지로 돌아가게 해주는 코
        category_back3.setOnClickListener {
            finish()
        }
    }
}
