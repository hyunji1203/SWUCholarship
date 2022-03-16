package com.example.main

import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frag1.*
import java.util.*

class Frag4 : Fragment() {

    //데이터베이스 이용을 위한 선언
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    var profileList = java.util.ArrayList<profiles>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag4, container, false)

        //디데이 계산을 위한 변수 선언
        var year = 0
        var month = 0
        var day = 0
        lateinit var last: String

        dbManager = DBManager (requireContext(), "august_day", null, 1)

        sqlitedb = dbManager.readableDatabase

        var a = 11

        //11월 장학금 리스트를 갖고 오기 위해 month값이 11인 데이터를 불러옴
        var cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)

        while (cursor.moveToNext()) {
            var first = cursor.getString((cursor.getColumnIndex("name")))
            var second = cursor.getString((cursor.getColumnIndex("period")))
            var third = cursor.getString((cursor.getColumnIndex("explain")))
            var forth = cursor.getString((cursor.getColumnIndex("type")))
            var fifth = cursor.getInt((cursor.getColumnIndex("typenum")))

            //가져온 값들은 리스트에 추
            profileList.add(profiles(first,second,third,forth,"", 0, fifth))
        }

        var x = 0

        var cursor2 = sqlitedb.rawQuery("SELECT * FROM august_day WHERE month = '"+a+"';", null)

        //디데이 계싼을 위해 필요한 값을 구해옴
        while (cursor2.moveToNext()) {
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

    //리사이클러뷰와 리스트를 연결하는 코드
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_profile.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)

        rv_profile.adapter = ProfileAdapter(profileList)
    }
}