package com.example.main

import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frag1.*
import java.util.*

class Frag1 : Fragment() {

    //데이터 베이스를 사용하기 위해 선언
    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    var profileList = java.util.ArrayList<profiles>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag1, container, false)

        //디데이 계싼을 위한 변수 선언
        var year = 0
        var month = 0
        var day = 0
        lateinit var last : String

        dbManager = DBManager(requireContext(), "august_day", null, 1)

        sqlitedb = dbManager.readableDatabase

        //'month'값이 8월인 데이터를 cursor에 넣고
        var a = 8

        var cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)

        //while문을 이용하여 필요한 값을을 가져옴
        while (cursor.moveToNext()){
            var first = cursor.getString((cursor.getColumnIndex("name")))
            var second = cursor.getString((cursor.getColumnIndex("period")))
            var third = cursor.getString((cursor.getColumnIndex("explain")))
            var forth = cursor.getString((cursor.getColumnIndex("type")))
            var fifth = cursor.getInt((cursor.getColumnIndex("typenum")))

            //가져온 값들은 리스트에 추
            profileList.add(profiles(first,second,third,forth,"", 0, fifth))
        }

        var x = 0
        //디데이 계산을 하기 위해 데이터 베이스에서 year, month, day 값을 가져옴
        var cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)

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

            profileList[x].day = last
            profileList[x].value = lastDate.toInt()
            x++

        }

        cursor.close()
        cursor2.close()
        sqlitedb.close()
        dbManager.close()
        //////////////////////////////////////////

        return view
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

    //프래그먼트 내 리사이클러뷰와 리스트의 아이템들을 연결해주는 코드
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_profile.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)

        rv_profile.adapter = ProfileAdapter(profileList)
    }
}