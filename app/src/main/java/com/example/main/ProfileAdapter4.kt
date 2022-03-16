package com.example.main

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter4(val profileList4: ArrayList<profiles4>) : RecyclerView.Adapter<ProfileAdapter4.CustomViewHolder4>() {

    lateinit var dbManager : DBManager_star
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter4.CustomViewHolder4 {

        val view : View?

        return when(viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.star_item_recycle,
                    parent,
                    false
                )
                CustomViewHolder4(view).apply {

                    //즐겨찾기 창에서 별을 눌렀을 때
                    star_del.setOnClickListener {

                        val dialog = CustomDialog(parent.context)
                        dialog.showDialog()
                        dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                            override fun onClicked()
                            {
                                val curPos : Int = adapterPosition
                                val profile: profiles4 = profileList4.get(curPos)

                                var name = profile.name

                                dbManager = DBManager_star(view.context, "star_list", null, 1)
                                sqlitedb = dbManager.writableDatabase

                                //별이 눌러진 아이템의 이름을 받아와 즐겨찾기 리스트 데이터베이스에서 삭제 함
                                sqlitedb.execSQL("DELETE FROM star_list WHERE name = '"+ name +"';")

                                var dbManager = DBManager(view.context, "august_day",null,1)
                                sqlitedb = dbManager.writableDatabase

                                //장학금 리스트 데이터베이스에서도 즐겨찾기 설정 값을 false로 바꿈
                                sqlitedb.execSQL("UPDATE august_day SET star = '"+0+"' WHERE name = '"+name+"';")

                                var intent = Intent(view.context, star1::class.java)
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
                    R.layout.star_item_recycle_y,
                    parent,
                    false
                )
                CustomViewHolder4(view).apply {

                    //즐겨찾기 창에서 별을 눌렀을 때
                    star_del.setOnClickListener {

                        val dialog = CustomDialog(parent.context)
                        dialog.showDialog()
                        dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                            override fun onClicked()
                            {
                                val curPos : Int = adapterPosition
                                val profile: profiles4 = profileList4.get(curPos)

                                var name = profile.name

                                dbManager = DBManager_star(view.context, "star_list", null, 1)
                                sqlitedb = dbManager.writableDatabase

                                //별이 눌러진 아이템의 이름을 받아와 즐겨찾기 리스트 데이터베이스에서 삭제 함
                                sqlitedb.execSQL("DELETE FROM star_list WHERE name = '"+ name +"';")

                                var dbManager = DBManager(view.context, "august_day",null,1)
                                sqlitedb = dbManager.writableDatabase

                                //장학금 리스트 데이터베이스에서도 즐겨찾기 설정 값을 false로 바꿈
                                sqlitedb.execSQL("UPDATE august_day SET star = '"+0+"' WHERE name = '"+name+"';")

                                var intent = Intent(view.context, star1::class.java)
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

    override fun onBindViewHolder(holder: ProfileAdapter4.CustomViewHolder4, position: Int) {
        holder.name.text = profileList4.get(position).name
        holder.star_del.setImageResource(profileList4.get(position).star_del)
    }


    class CustomViewHolder4 (itemVIew : View) : RecyclerView.ViewHolder(itemVIew) {
        val name = itemVIew.findViewById<TextView>(R.id.nation_name)
        val star_del = itemVIew.findViewById<ImageView>(R.id.star_del)
    }

    override fun getItemCount(): Int {
        return profileList4.size
    }

    override fun getItemViewType(position: Int): Int {
        return profileList4[position].typenum
    }

}