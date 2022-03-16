package com.example.main.todo

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Handler
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.*
import com.example.main.search.Search
import kotlinx.android.synthetic.main.activity_calculate.*
import kotlinx.android.synthetic.main.activity_calculate.main_drawer_layout
import kotlinx.android.synthetic.main.activity_calculate.menu01
import kotlinx.android.synthetic.main.activity_calculate.menu02
import kotlinx.android.synthetic.main.activity_calculate.menu03
import kotlinx.android.synthetic.main.activity_calculate.menu04
import kotlinx.android.synthetic.main.main_drawer_header.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var homeBtn: ImageButton
    lateinit var infoBtn: ImageButton
    lateinit var ringBtn: ImageButton
    lateinit var myBtn: ImageButton
    lateinit var starBtn: ImageButton

    //드로어 여는 버튼
    lateinit var btnOpen : ImageButton

    lateinit var firstMoney : TextView
    lateinit var secondMoney : TextView
    lateinit var totalMoney : TextView

    lateinit var dbManager : DBManager_cost
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var db: SQLiteDatabase

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)
        setSupportActionBar(findViewById<Toolbar>(R.id.main_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

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
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }


        // 장학금 도전
        dbHandler = DBHandler(this)
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddPop.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.new_task, null)
            val toDoName = view.findViewById<EditText>(R.id.etTodoTitle)
            dialog.setTitle("TodoDialog")
            dialog.setView(view)
            dialog.setPositiveButton("입력") { _: DialogInterface, _: Int ->
                if (toDoName.text.isNotEmpty()) {
                    val toDo = ToDo()
                    toDo.name = toDoName.text.toString()
                    dbHandler.addToDo(toDo)
                    refreshList()
                }
            }
            dialog.setNegativeButton("취소") { _: DialogInterface, _: Int ->

            }
            dialog.show()

        }

        btnDeleteDoneTodos.setOnClickListener {
            refreshList()

            trophy.visibility = View.VISIBLE

            Handler().postDelayed({
                trophy.visibility = View.INVISIBLE
            }, 1000) // 1초

        }

        homeBtn = findViewById(R.id.homeBtn5)
        infoBtn = findViewById(R.id.infoBtn5)
        myBtn = findViewById(R.id.myBtn5)
        ringBtn = findViewById(R.id.ringBtn5)
        starBtn = findViewById(R.id.starBtn5)

        firstMoney = findViewById(R.id.firstMoney)
        secondMoney = findViewById(R.id.secondMoney)
        totalMoney = findViewById(R.id.totalMoney)

        var arrayList =  ArrayList<Int>()

        var s_money = 0
        var t_money = 0

        var dbManager = DBManager_cost(this, "cost_list", null, 1)
        sqlitedb = dbManager.writableDatabase

        //현재 알람설정 데이터베이스에 저장된 리스트들을 데려와 arrayList에 저장하는 코드
        var cursor = sqlitedb.rawQuery("SELECT * FROM cost_list", null)

        while (cursor.moveToNext()){
            var cost = cursor.getInt((cursor.getColumnIndex("cost")))

            arrayList.add(cost)
        }

        for (i in arrayList){
            s_money += i
        }

        secondMoney.text = s_money.toString()

        t_money = 4037000 - s_money
        totalMoney.text = t_money.toString()

        cursor.close()
        sqlitedb.close()
        dbManager.close()

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
        homeBtn.setOnClickListener {
            var intent = Intent(this, MainActivity_home::class.java)
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

    override fun onResume() {
        refreshList()
        super.onResume()
    }

    private fun refreshList() {
        rvTodoItems.adapter = DashboardAdapter(this, dbHandler.getToDos())
    }

    fun deleteToDo(todoId: Long) {
        db = dbHandler.writableDatabase
        db.delete(TABLE_TODO, "$COL_ID=?", arrayOf(todoId.toString()))
    }

    class DashboardAdapter(val activity: MainActivity, val list: MutableList<ToDo>) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.task_layout, p0, false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
            holder.toDoName.text = list[p1].name

            holder.check.setOnClickListener{
                activity.deleteToDo(list[p1].id)
            }

            // 간격 설정
            val layoutParams = holder.itemView.layoutParams
            layoutParams.height = 100
            holder.itemView.requestLayout()
        }

        class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
            val toDoName : TextView = v.findViewById(R.id.tvTodoTitle)
            val check : TextView = v.findViewById(R.id.cbDone)
        }
    }
}