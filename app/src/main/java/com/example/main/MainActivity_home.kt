package com.example.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.main.databinding.ActivityMainHomeBinding
import com.example.main.search.Search
import com.example.main.todo.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main_home.*
import kotlinx.android.synthetic.main.main_drawer_header.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity_home : AppCompatActivity() {
    //메뉴 버튼 선언
    lateinit var homeBtn: ImageButton
    lateinit var infoBtn: ImageButton
    lateinit var ringBtn: ImageButton
    lateinit var myBtn: ImageButton
    lateinit var starBtn: ImageButton
    lateinit var main_month : TextView
    lateinit var btn_back : ImageView
    lateinit var btn_forward : ImageView

    //드로어 여는 버튼
    lateinit var btnOpen : ImageButton

    //해시태그 선언
    lateinit var a_hash1 : ImageButton
    lateinit var a_hash2 : ImageButton
    lateinit var a_hash3 : ImageButton
    lateinit var a_hash4 : ImageButton
    lateinit var a_hash5 : ImageButton
    lateinit var a_hash6 : ImageButton

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)
        //setContentView(binding.root) //드로어 레이아웃 설정

        //main 툴바를 설정
        setSupportActionBar(findViewById<Toolbar>(R.id.main_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        //메뉴 변수와 버튼 연결
        homeBtn = findViewById(R.id.homeBtn5)
        infoBtn = findViewById(R.id.infoBtn5)
        myBtn = findViewById(R.id.myBtn5)
        ringBtn = findViewById(R.id.ringBtn5)
        starBtn = findViewById(R.id.starBtn5)
        main_month = findViewById(R.id.main_month)
        btn_back = findViewById(R.id.btn_back)
        btn_forward = findViewById(R.id.btn_forward)
        btnOpen = findViewById(R.id.btnOpen)

        //선언 변수 해시태그와 연결시키기
        a_hash1 = findViewById(R.id.m_hash1)
        a_hash2 = findViewById(R.id.m_hash2)
        a_hash3 = findViewById(R.id.m_hash3)
        a_hash4 = findViewById(R.id.m_hash4)
        a_hash5 = findViewById(R.id.m_hash5)
        a_hash6 = findViewById(R.id.m_hash6)

        //커스텀 툴바를 액션바로 바꿔주는 코드
        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        var dbHelper: LoginDB = LoginDB(applicationContext, "MEMBER.db", null, 1)

        //햄버거 버튼으로 드로어 열기
        btnOpen.setOnClickListener {
            // 아이디를 보여줌
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

        ///setViewPager()

        //다시 여기서부터 장학금 리스트 프래그먼트 관련 설정
        var n = 3
        setFrag(n)

        main_month.text = "11월"

        //화살표 버튼 클릭시 달별 장학금 화면을 넘겨주는 코드
        btn_forward.setOnClickListener {
            if(n == 0 || n == 1 || n == 2 || n == 3 || n == 4){
                n++
            }

            when (n) {
                0 -> {
                    //8월 장학금 리스트를 보여줌
                    main_month.text = "8월"
                    setFrag(0)
                }
                1 -> {
                    //9월 장학금 리스트를 보여줌
                    main_month.text = "9월"
                    setFrag(1)
                }
                2-> {
                    //10월 장학금 리스트를 보여줌
                    main_month.text = "10월"
                    setFrag(2)
                }
                3 -> {
                    //11월 장학금 리스트를 보여줌
                    main_month.text = "11월"
                    setFrag(3)
                }
                4 -> {
                    //12월 장학금 리스트를 보여줌
                    main_month.text = "12월"
                    setFrag(4)
                }
                5 -> {
                    //기간 미정인 장학금 리스트를 보여줌
                    main_month.text = "그 외"
                    setFrag(5)
                }
            }
        }
        btn_back.setOnClickListener {
            if(n == 2 || n == 1 || n == 3 || n == 4 || n == 5){
                n--
            }


            when (n) {
                0 -> {
                    main_month.text = "8월"
                    setFrag(0)
                }
                1 -> {
                    main_month.text = "9월"
                    setFrag(1)
                }
                2-> {
                    main_month.text = "10월"
                    setFrag(2)
                }
                3-> {
                    main_month.text = "11월"
                    setFrag(3)
                }
                4 -> {
                    main_month.text = "12월"
                    setFrag(4)
                }
                5-> {
                    main_month.text = "그 외"
                    setFrag(5)
                }
            }
        }

        //달 넘기는 버튼
        main_month.setOnClickListener {
            var now_month = main_month.text

            var intent = Intent(this, monthjang::class.java)
            intent.putExtra("month",now_month)
            startActivity(intent)
        }

        //메뉴 버튼 클릭 시 화면 이동
        infoBtn.setOnClickListener {
            var intent = Intent(this, monthjang::class.java)
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

        // 푸시 알림
        if (fewDay == null) {
            fun passPushTokenToServer() {
                val uid: String = FirebaseAuth.getInstance().currentUser!!.getUid()
                val token = FirebaseInstanceId.getInstance().token
                val map = HashMap<String, Any>()
                if (token != null) {
                    map.put("pushToken", token)
                }
                FirebaseDatabase.getInstance().getReference().child("users").child(uid).updateChildren(map)
            }
        }
    }

    //메뉴를 인식시키기
    @SuppressLint("ResourceType")
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

    fun getIgnoreTimeDays(time: Long): Long {
        return Calendar.getInstance().apply {
            timeInMillis = time

            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    //프래그먼트 넘기기
    private fun setFrag(fragnum : Int) {
        val ft = supportFragmentManager.beginTransaction()

        when (fragnum) {
            0 -> {
                ft.replace(R.id.main_frame, Frag1()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, Frag2()).commit()
            }
            2 -> {
                ft.replace(R.id.main_frame, Frag3()).commit()
            }
            3 -> {
                ft.replace(R.id.main_frame, Frag4()).commit()
            }
            4 -> {
                ft.replace(R.id.main_frame, Frag_Dec()).commit()
            }
            5 -> {
                ft.replace(R.id.main_frame, Frag5()).commit()
            }
        }
    }

    //드로어 레이아웃 페이지 넘기는 함수
    /*private fun movePager(index:Int) {
        binding.viewPager.currentItem = index
        binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
    }*/

    //드로어 레이아웃 뷰페이저 안에 들어갈 프래그먼트 설정 및 어댑터 설정
    /*private fun setViewPager() {
        val fragmentList = listOf(FragmentA(), FragmentB(), FragmentC())
        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList
        binding.viewPager.adapter = adapter
    }*/

    //fragment 교체
    /*fun replaceFragment(fragment:Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }*/

}
