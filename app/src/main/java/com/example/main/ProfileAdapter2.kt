package com.example.main
//알람관련페이지
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter2(val profileList2: ArrayList<profiles2>) : RecyclerView.Adapter<ProfileAdapter2.CustomViewHolder2>() {
//우측 상단에 알림과 즐겨찾기 표시가 나와있는 리스트를 리사이클러뷰에 연결하는 어댑터

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter2.CustomViewHolder2 {

        val view : View?

        return when(viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_recycle2,
                    parent,
                    false
                )
                CustomViewHolder2(view).apply {

                    ///리스트 클릭시 장학금 이름을 상세설명창 페이지로 넘겨주는 코드
                    itemView.setOnClickListener {
                        val curPos : Int = adapterPosition
                        val profile: profiles2 = profileList2.get(curPos)

                        var myExam = Exam(profile.name, profile.day2, profile.value, profile.typenum)

                        var intent = Intent(view.context, testExplain::class.java)
                        intent.putExtra("examKey", myExam)
                        view.context.startActivity(intent)
                    }
                    //알림 클릭을 했을 때
                    ring.setOnClickListener {
                        val curPos : Int = adapterPosition
                        val profile: profiles2 = profileList2.get(curPos)
                        var name = profile.name

                        lateinit var dbManager : DBManager
                        lateinit var sqlitedb : SQLiteDatabase

                        dbManager = DBManager(view.context, "august_day", null, 1)
                        sqlitedb = dbManager.readableDatabase

                        var cursor : Cursor
                        cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE name = '"+name+"';", null)

                        var x = 0
                        var y = 0

                        while (cursor.moveToNext()) {
                            x = cursor.getInt((cursor.getColumnIndex("bool")))
                            y = cursor.getInt((cursor.getColumnIndex("cost")))
                        }

                        if (x == 0){   //알림 설정이 안되었을 때 클릭 시
                            ring.setImageResource(R.drawable.alarm_y)

                            var myExam = Exam(profile.name, profile.day2, profile.value, profile.typenum)

                            //알림 설정 데이터 베이스에 리스트 추가함
                            sqlitedb.execSQL("UPDATE august_day SET bool = '"+1+"' WHERE name = '"+name+"';")


                            var dbManager = DBManager_cost(view.context, "cost_list", null, 1)
                            sqlitedb = dbManager.writableDatabase

                            sqlitedb.execSQL("INSERT INTO cost_list VALUES ('" +name+ "', '" +y+ "')")

                            sqlitedb.close()
                            dbManager.close()

                            var intent = Intent(view.context, alarm::class.java)
                            intent.putExtra("examKey", myExam)
                            view.context.startActivity(intent)
                        }
                        else{ //알림 설정이 되어있을 때 클릭 시
                            ring.setImageResource(R.drawable.alarm_gray)
                            sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+name+"';")

                            var dbManager = DBManager_alarm(view.context, "alarm_list", null, 1)
                            sqlitedb = dbManager.readableDatabase

                            //알림 설정 데이터 베이스에서 제거함
                            sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ name +"';")

                            var dbManager_cost = DBManager_cost(view.context, "cost_list", null, 1)
                            sqlitedb = dbManager_cost.writableDatabase

                            //등록금 계산기 데이터 베이스에서 제거함
                            sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ name +"';")

                            sqlitedb.close()
                            dbManager.close()
                        }
                    }

                    //즐겨찾기 클릭을 했을 때
                    star.setOnClickListener {
                        val curPos : Int = adapterPosition
                        val profile: profiles2 = profileList2.get(curPos)
                        var name = profile.name

                        lateinit var dbManager : DBManager
                        lateinit var sqlitedb : SQLiteDatabase

                        dbManager = DBManager(view.context, "august_day", null, 1)
                        sqlitedb = dbManager.readableDatabase

                        var cursor : Cursor
                        cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE name = '"+name+"';", null)

                        var x = 0
                        while (cursor.moveToNext()) {
                            x = cursor.getInt((cursor.getColumnIndex("star")))
                        }

                        if (x == 0){  //즐겨찾기 설정이 안되어 있을 때 클릭 시
                            star.setImageResource(R.drawable.star_y)

                            var myExam = Exam(profile.name, profile.day2, profile.value, profile.typenum)

                            //즐겨찾기 관리 데이터베이스 리스트에 추가함
                            sqlitedb.execSQL("UPDATE august_day SET star = '"+1+"' WHERE name = '"+name+"';")

                            cursor.close()
                            dbManager.close()
                            sqlitedb.close()

                            //즐겨찾기 창으로 이동
                            var intent2 = Intent(view.context, star1::class.java)
                            intent2.putExtra("examKey", myExam)
                            view.context.startActivity(intent2)
                        }
                        else{ //즐겨찾기 설정이 되었을 때 클릭 시
                            star.setImageResource(R.drawable.star_gray)
                            sqlitedb.execSQL("UPDATE august_day SET star = '"+0+"' WHERE name = '"+name+"';")

                            var dbManager = DBManager_star(view.context, "star_list", null, 1)
                            sqlitedb = dbManager.writableDatabase

                            //즐겨찾기 리스트 데이터 베이스에서 이름을 제거함
                            sqlitedb.execSQL("DELETE FROM star_list WHERE name = '"+ name +"';")

                            sqlitedb.close()
                            dbManager.close()
                        }
                    }
                }
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_recycle2_y,
                    parent,
                    false
                )
                CustomViewHolder2(view).apply {

                    //리스트 클릭시 장학금 이름을 상세설명창 페이지로 넘겨주는 코드
                    itemView.setOnClickListener {
                        val curPos : Int = adapterPosition
                        val profile: profiles2 = profileList2.get(curPos)

                        var myExam = Exam(profile.name, profile.day2, profile.value, profile.typenum)

                        var intent = Intent(view.context, testExplain::class.java)
                        intent.putExtra("examKey", myExam)
                        view.context.startActivity(intent)
                    }

                    //알림 클릭을 했을 때
                    ring.setOnClickListener {

                        val curPos : Int = adapterPosition
                        val profile: profiles2 = profileList2.get(curPos)
                        var name = profile.name

                        lateinit var dbManager : DBManager
                        lateinit var sqlitedb : SQLiteDatabase

                        dbManager = DBManager(view.context, "august_day", null, 1)
                        sqlitedb = dbManager.readableDatabase

                        var cursor : Cursor
                        cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE name = '"+name+"';", null)

                        var x = 0
                        var y = 0

                        while (cursor.moveToNext()) {
                            x = cursor.getInt((cursor.getColumnIndex("bool")))
                            y = cursor.getInt((cursor.getColumnIndex("cost")))
                        }

                        if (x == 0){   //알림 설정이 안되었을 때 클릭 시
                            ring.setImageResource(R.drawable.alarm_y)

                            var myExam = Exam(profile.name, profile.day2, profile.value, profile.typenum)

                            //알림 설정 데이터 베이스에 리스트 추가함
                            sqlitedb.execSQL("UPDATE august_day SET bool = '"+1+"' WHERE name = '"+name+"';")


                            var dbManager = DBManager_cost(view.context, "cost_list", null, 1)
                            sqlitedb = dbManager.writableDatabase

                            sqlitedb.execSQL("INSERT INTO cost_list VALUES ('" +name+ "', '" +y+ "')")

                            sqlitedb.close()
                            dbManager.close()

                            var intent = Intent(view.context, alarm::class.java)
                            intent.putExtra("examKey", myExam)
                            view.context.startActivity(intent)

                        }
                        else{ //알림 설정이 되어있을 때 클릭 시
                            ring.setImageResource(R.drawable.alarm_gray)
                            sqlitedb.execSQL("UPDATE august_day SET bool = '"+0+"' WHERE name = '"+name+"';")

                            var dbManager = DBManager_alarm(view.context, "alarm_list", null, 1)
                            sqlitedb = dbManager.readableDatabase

                            //알림 설정 데이터 베이스에서 제거함
                            sqlitedb.execSQL("DELETE FROM alarm_list WHERE name = '"+ name +"';")

                            var dbManager_cost = DBManager_cost(view.context, "cost_list", null, 1)
                            sqlitedb = dbManager_cost.writableDatabase

                            //등록금 계산기 데이터 베이스에서 제거함
                            sqlitedb.execSQL("DELETE FROM cost_list WHERE name = '"+ name +"';")

                            sqlitedb.close()
                            dbManager.close()
                        }
                    }

                    //즐겨찾기 클릭을 했을 때
                    star.setOnClickListener {
                        val curPos : Int = adapterPosition
                        val profile: profiles2 = profileList2.get(curPos)
                        var name = profile.name

                        lateinit var dbManager : DBManager
                        lateinit var sqlitedb : SQLiteDatabase

                        dbManager = DBManager(view.context, "august_day", null, 1)
                        sqlitedb = dbManager.readableDatabase

                        var cursor : Cursor
                        cursor = sqlitedb.rawQuery("SELECT * FROM august_day WHERE name = '"+name+"';", null)

                        var x = 0
                        while (cursor.moveToNext()) {
                            x = cursor.getInt((cursor.getColumnIndex("star")))
                        }

                        if (x == 0){  //즐겨찾기 설정이 안되어 있을 때 클릭 시
                            star.setImageResource(R.drawable.star_y)

                            var myExam = Exam(profile.name, profile.day2,profile.value, profile.typenum)

                            //즐겨찾기 관리 데이터베이스 리스트에 추가함
                            sqlitedb.execSQL("UPDATE august_day SET star = '"+1+"' WHERE name = '"+name+"';")

                            cursor.close()
                            dbManager.close()
                            sqlitedb.close()

                            //즐겨찾기 창으로 이동
                            var intent2 = Intent(view.context, star1::class.java)
                            intent2.putExtra("examKey", myExam)
                            view.context.startActivity(intent2)
                        }
                        else{ //즐겨찾기 설정이 되었을 때 클릭 시
                            star.setImageResource(R.drawable.star_gray)
                            sqlitedb.execSQL("UPDATE august_day SET star = '"+0+"' WHERE name = '"+name+"';")

                            var dbManager = DBManager_star(view.context, "star_list", null, 1)
                            sqlitedb = dbManager.writableDatabase

                            //즐겨찾기 리스트 데이터 베이스에서 이름을 제거함
                            sqlitedb.execSQL("DELETE FROM star_list WHERE name = '"+ name +"';")

                            sqlitedb.close()
                            dbManager.close()
                        }
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ProfileAdapter2.CustomViewHolder2, position: Int) {
        holder.name.text = profileList2.get(position).name
        holder.period.text = profileList2.get(position).period
        holder.explain.text = profileList2.get(position).explain
        holder.type.text = profileList2.get(position).type
        holder.ring.setImageResource(profileList2.get(position).ring)
        holder.star.setImageResource(profileList2.get(position).star)
        holder.day2.text = profileList2.get(position).day2
        holder.value.text = profileList2.get(position).value.toString()
        holder.bool.text = profileList2.get(position).bool.toString()
        holder.star_x.text = profileList2.get(position).star_x.toString()
    }


    class CustomViewHolder2 (itemVIew : View) :RecyclerView.ViewHolder(itemVIew) {


        val name = itemVIew.findViewById<TextView>(R.id.nation_name)
        val period = itemVIew.findViewById<TextView>(R.id.nation_period)
        val explain = itemVIew.findViewById<TextView>(R.id.nation_explain)
        val type = itemView.findViewById<TextView>(R.id.nation_type)
        val ring = itemVIew.findViewById<ImageView>(R.id.ring)
        val star = itemVIew.findViewById<ImageView>(R.id.star_add)
        val day2 = itemVIew.findViewById<TextView>(R.id.day2)
        val value = itemVIew.findViewById<TextView>(R.id.value)
        val bool = itemVIew.findViewById<TextView>(R.id.bool)
        val star_x = itemVIew.findViewById<TextView>(R.id.star_x)
    }

    override fun getItemCount(): Int {
        return profileList2.size
    }

    override fun getItemViewType(position: Int): Int {
        return profileList2[position].typenum
    }

}