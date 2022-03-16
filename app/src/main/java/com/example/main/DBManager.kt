package com.example.main

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//데이터 베이스 데려올 때 도와주는 코드
class DBManager(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE august_day (year INTEGER, month INTEGER, day INTEGER, name text, period text, explain text, type text, bool INTEGER, star INTEGER, tgrage INTEGER, double INTEGER, money INTEGER, life INTEGER, rel INTEGER, now INTEGER )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
