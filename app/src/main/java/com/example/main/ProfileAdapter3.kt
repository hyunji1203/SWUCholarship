package com.example.main

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter3(val profileList3: ArrayList<profiles3>) : RecyclerView.Adapter<ProfileAdapter3.CustomViewHolder3>() {

    lateinit var dbManager : DBManager_alarm
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter3.CustomViewHolder3 {

        val view: View?

        return when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.alarm_item_recycle_r,
                    parent,
                    false
                )
                CustomViewHolder3(view).apply {

                    //알림 창에서 디데이를 눌렀을 때
                    day3.setOnClickListener {

                        val dialog = CustomDialog(parent.context)
                        dialog.showDialog()
                        dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                            override fun onClicked()
                            {
                                val curPos : Int = adapterPosition
                                val profile: profiles3 = profileList3.get(curPos)

                                var name = profile.name

                                dbManager = DBManager_alarm(view.context, "alarm_list", null, 1)
                                sqlitedb = dbManager.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 알람을 해제함
                                sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ name +"';")

                                var dbManager = DBManager(view.context, "august_day",null,1)
                                sqlitedb = dbManager.writableDatabase

                                //장학금 리스트에 알림 설정 값을 false로 바꿈
                                sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+name+"';")

                                var dbManager_cost = DBManager_cost(view.context, "cost_list", null, 1)
                                sqlitedb = dbManager_cost.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 등록금을 해제함
                                sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ name +"';")

                                var intent = Intent(view.context, alarm::class.java)
                                view.context.startActivity(intent)

                                sqlitedb.close()
                                dbManager.close()
                            }

                        })
                    }
                }
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.alarm_item_recycle_r_yy,
                    parent,
                    false
                )
                CustomViewHolder3(view).apply {

                    //알림 창에서 디데이를 눌렀을 때
                    day3.setOnClickListener {

                        val dialog = CustomDialog(parent.context)
                        dialog.showDialog()
                        dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                            override fun onClicked()
                            {
                                val curPos : Int = adapterPosition
                                val profile: profiles3 = profileList3.get(curPos)

                                var name = profile.name

                                dbManager = DBManager_alarm(view.context, "alarm_list", null, 1)
                                sqlitedb = dbManager.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 알람을 해제함
                                sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ name +"';")

                                var dbManager = DBManager(view.context, "august_day",null,1)
                                sqlitedb = dbManager.writableDatabase

                                //장학금 리스트에 알림 설정 값을 false로 바꿈
                                sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+name+"';")

                                var dbManager_cost = DBManager_cost(view.context, "cost_list", null, 1)
                                sqlitedb = dbManager_cost.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 등록금을 해제함
                                sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ name +"';")

                                var intent = Intent(view.context, alarm::class.java)
                                view.context.startActivity(intent)

                                sqlitedb.close()
                                dbManager.close()
                            }

                        })
                    }
                }
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.alarm_item_recycle_y,
                    parent,
                    false
                )
                CustomViewHolder3(view).apply {

                    //알림 창에서 디데이를 눌렀을 때
                    day3.setOnClickListener {

                        val dialog = CustomDialog(parent.context)
                        dialog.showDialog()
                        dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                            override fun onClicked()
                            {
                                val curPos : Int = adapterPosition
                                val profile: profiles3 = profileList3.get(curPos)

                                var name = profile.name

                                dbManager = DBManager_alarm(view.context, "alarm_list", null, 1)
                                sqlitedb = dbManager.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 알람을 해제함
                                sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ name +"';")

                                var dbManager = DBManager(view.context, "august_day",null,1)
                                sqlitedb = dbManager.writableDatabase

                                //장학금 리스트에 알림 설정 값을 false로 바꿈
                                sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+name+"';")

                                var dbManager_cost = DBManager_cost(view.context, "cost_list", null, 1)
                                sqlitedb = dbManager_cost.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 등록금을 해제함
                                sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ name +"';")

                                var intent = Intent(view.context, alarm::class.java)
                                view.context.startActivity(intent)

                                sqlitedb.close()
                                dbManager.close()
                            }

                        })
                    }
                }
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.alarm_item_recycle_y_yy,
                    parent,
                    false
                )
                CustomViewHolder3(view).apply {

                    //알림 창에서 디데이를 눌렀을 때
                    day3.setOnClickListener {

                        val dialog = CustomDialog(parent.context)
                        dialog.showDialog()
                        dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                            override fun onClicked()
                            {
                                val curPos : Int = adapterPosition
                                val profile: profiles3 = profileList3.get(curPos)

                                var name = profile.name

                                dbManager = DBManager_alarm(view.context, "alarm_list", null, 1)
                                sqlitedb = dbManager.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 알람을 해제함
                                sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ name +"';")

                                var dbManager = DBManager(view.context, "august_day",null,1)
                                sqlitedb = dbManager.writableDatabase

                                //장학금 리스트에 알림 설정 값을 false로 바꿈
                                sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+name+"';")

                                var dbManager_cost = DBManager_cost(view.context, "cost_list", null, 1)
                                sqlitedb = dbManager_cost.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 등록금을 해제함
                                sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ name +"';")

                                var intent = Intent(view.context, alarm::class.java)
                                view.context.startActivity(intent)

                                sqlitedb.close()
                                dbManager.close()
                            }

                        })
                    }
                }
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.alarm_item_recycle,
                    parent,
                    false
                )
                CustomViewHolder3(view).apply {

                    //알림 창에서 디데이를 눌렀을 때
                    day3.setOnClickListener {

                        val dialog = CustomDialog(parent.context)
                        dialog.showDialog()
                        dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                            override fun onClicked()
                            {
                                val curPos : Int = adapterPosition
                                val profile: profiles3 = profileList3.get(curPos)

                                var name = profile.name

                                dbManager = DBManager_alarm(view.context, "alarm_list", null, 1)
                                sqlitedb = dbManager.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 알람을 해제함
                                sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ name +"';")

                                var dbManager = DBManager(view.context, "august_day",null,1)
                                sqlitedb = dbManager.writableDatabase

                                //장학금 리스트에 알림 설정 값을 false로 바꿈
                                sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+name+"';")

                                var dbManager_cost = DBManager_cost(view.context, "cost_list", null, 1)
                                sqlitedb = dbManager_cost.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 등록금을 해제함
                                sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ name +"';")

                                var intent = Intent(view.context, alarm::class.java)
                                view.context.startActivity(intent)

                                sqlitedb.close()
                                dbManager.close()
                            }

                        })
                    }
                }
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.alarm_item_recycle_yy,
                    parent,
                    false
                )
                CustomViewHolder3(view).apply {

                    //알림 창에서 디데이를 눌렀을 때
                    day3.setOnClickListener {

                        val dialog = CustomDialog(parent.context)
                        dialog.showDialog()
                        dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                            override fun onClicked()
                            {
                                val curPos : Int = adapterPosition
                                val profile: profiles3 = profileList3.get(curPos)

                                var name = profile.name

                                dbManager = DBManager_alarm(view.context, "alarm_list", null, 1)
                                sqlitedb = dbManager.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 알람을 해제함
                                sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ name +"';")

                                var dbManager = DBManager(view.context, "august_day",null,1)
                                sqlitedb = dbManager.writableDatabase

                                //장학금 리스트에 알림 설정 값을 false로 바꿈
                                sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+name+"';")

                                var dbManager_cost = DBManager_cost(view.context, "cost_list", null, 1)
                                sqlitedb = dbManager_cost.writableDatabase

                                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 등록금을 해제함
                                sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ name +"';")

                                var intent = Intent(view.context, alarm::class.java)
                                view.context.startActivity(intent)

                                sqlitedb.close()
                                dbManager.close()
                            }

                        })
                    }
                }
            }
        }
    }
    override fun onBindViewHolder(holder: ProfileAdapter3.CustomViewHolder3, position: Int) {
        holder.name.text = profileList3.get(position).name
        holder.value2.text = profileList3.get(position).value2.toString()
        holder.day3.text = profileList3.get(position).day3

    }

    class CustomViewHolder3 (itemVIew : View) : RecyclerView.ViewHolder(itemVIew) {

            val name = itemVIew.findViewById<TextView>(R.id.nation_name)
            val day3 = itemVIew.findViewById<TextView>(R.id.day3)
            val value2 = itemVIew.findViewById<TextView>(R.id.value2)
        }

    override fun getItemCount(): Int {
        return profileList3.size
    }

    override fun getItemViewType(position: Int): Int {
        if ((profileList3[position].value2 >= 0 ) && (profileList3[position].value2 <=3) && (profileList3[position].typenum == 1)){
            return 1
        }
        else if ((profileList3[position].value2 >= 0 ) && (profileList3[position].value2 <=3) && (profileList3[position].typenum == 2)){
            return 2
        }
        else if ((profileList3[position].value2 > 3) && (profileList3[position].value2 <= 14) && (profileList3[position].typenum == 1)){
            return 3
        }
        else if ((profileList3[position].value2 > 3) && (profileList3[position].value2 <= 14) && (profileList3[position].typenum == 2)){
            return 4
        }
        else if (profileList3[position].typenum == 1){
            return 5
        }
        else if (profileList3[position].typenum == 2){
            return 6
        }
        else{
            return 6
        }
    }
}