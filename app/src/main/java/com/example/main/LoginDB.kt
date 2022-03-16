package com.example.main

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

@Suppress("DEPRECATION")
class LoginDB (context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(
                "CREATE TABLE MEMBER(EMAIL TEXT," + "PASSWORD TEXT);"
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insert(email: String, password: String) {
        var db: SQLiteDatabase = writableDatabase

        db.execSQL(
            "INSERT INTO MEMBER VALUES('" + email + "'" + ", '" + password + "');"
        )
        db.close()
    }

    fun getResult1(EMAIL: String, PASSWORD: String): Boolean {
        var db: SQLiteDatabase = readableDatabase
        var result: String = ""

        var cursor: Cursor = db.rawQuery("SELECT * FROM MEMBER", null)
        while (cursor.moveToNext()) {
            result = (cursor.getString(0))
            if (result.equals(EMAIL)) {
                if (cursor.getString(1).equals(PASSWORD)) {
                    return true
                    break
                } else {
                    return false
                }
            } else {

            }
        }

        return false
    }
}