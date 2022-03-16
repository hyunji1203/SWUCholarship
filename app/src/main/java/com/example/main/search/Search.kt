package com.example.main.search

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.DBManager
import com.example.main.Exam
import com.example.main.R
import com.example.main.testExplain
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.item.*
import kotlinx.android.synthetic.main.secondsearch.searchBtn2
import kotlinx.android.synthetic.main.secondsearch.searchWord2
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.secondsearch.*

class Search : AppCompatActivity() {

    var firestore: FirebaseFirestore? = null

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondsearch)

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        recyclerview.adapter = RecyclerViewAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)

        // 검색 옵션 변수
        var searchOption = "name"

        // 검색 옵션에 따라 검색
        searchBtn2.setOnClickListener {
            (recyclerview.adapter as RecyclerViewAdapter).search(searchWord2.text.toString(), searchOption)

        }

        // 뒤로가기 버튼을 누르면 메인검색창으로 넘어감
        back2.setOnClickListener {
            val intent = Intent(this, searchMainActivity::class.java)
            startActivity(intent)
        }
    }


    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        // scholarship 클래스 ArrayList 생성
        var suburbs_list: ArrayList<scholarship> = arrayListOf()

        init { // suburbs_list의 문서를 불러온 뒤 scholarship으로 변환해 ArrayList에 담음
            firestore?.collection("suburbs_list")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                suburbs_list.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(scholarship::class.java)
                    suburbs_list.add(item!!)
                }
                notifyDataSetChanged()
            }
        }

        // xml파일을 inflate하여 ViewHolder를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

            ViewHolder(view).apply {
                itemView.setOnClickListener {
                    var name = name.text.toString()

                    var myExam = Exam(name, "", 0, 0)

                    var intent = Intent(view.context, testExplain::class.java)
                    intent.putExtra("examKey", myExam)
                    view.context.startActivity(intent)
                }
            }

            return ViewHolder(view)
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        }

        // onCreateViewHolder에서 만든 view와 실제 데이터를 연결
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView

            viewHolder.name.text = suburbs_list[position].name
        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return suburbs_list.size
        }

        // 파이어스토어에서 데이터를 불러와서 검색어가 있는지 판단
        fun search(searchWord2: String, option: String) {
            firestore?.collection("suburbs_list")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                suburbs_list.clear()

                for (snapshot in querySnapshot!!.documents) {
                    if (snapshot.getString(option)!!.contains(searchWord2)) {
                        var item = snapshot.toObject(scholarship::class.java)
                        suburbs_list.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
        }
    }
}