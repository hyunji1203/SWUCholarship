package com.example.main
//최현지

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter (val profileList : ArrayList<profiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>() {
   //디데이가 우측 상단에 있는 리스트를 리사이클러뷰에 연결시켜주는 어댑터

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.CustomViewHolder {

       val view : View?

        return when(viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_recycle,
                    parent,
                    false
                )
                CustomViewHolder(view).apply {

                    //아이템 클릭 시 해당 장학금의 이름이 상세설명 창으로 넘어가도록 intent이용하는 코드 작성
                    itemView.setOnClickListener {
                        val curPos : Int = adapterPosition
                        val profile: profiles = profileList.get(curPos)


                        var myExam = Exam(profile.name, profile.day, profile.value, profile.typenum)

                        var intent = Intent(view.context, testExplain::class.java)
                        intent.putExtra("examKey", myExam)
                        view.context.startActivity(intent)
                    }
                }
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_recycle_y,
                    parent,
                    false
                )
                CustomViewHolder(view).apply {

                    //아이템 클릭 시 해당 장학금의 이름이 상세설명 창으로 넘어가도록 intent이용하는 코드 작성
                    itemView.setOnClickListener {
                        val curPos : Int = adapterPosition
                        val profile: profiles = profileList.get(curPos)


                        var myExam = Exam(profile.name, profile.day, profile.value, profile.typenum)

                        var intent = Intent(view.context, testExplain::class.java)
                        intent.putExtra("examKey", myExam)
                        view.context.startActivity(intent)
                    }

                }
            }
        }
    }

    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {
        holder.name.text = profileList.get(position).name
        holder.period.text = profileList.get(position).period
        holder.explain.text = profileList.get(position).explain
        holder.type.text = profileList.get(position).type
        holder.day.text = profileList.get(position).day

    }


    class CustomViewHolder (itemVIew : View) :RecyclerView.ViewHolder(itemVIew) {

        val name = itemVIew.findViewById<TextView>(R.id.nation_name)
        val period = itemVIew.findViewById<TextView>(R.id.nation_period)
        val explain = itemVIew.findViewById<TextView>(R.id.nation_explain)
        val type = itemView.findViewById<TextView>(R.id.nation_type)
        val day = itemVIew.findViewById<TextView>(R.id.day)

    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun getItemViewType(position: Int): Int {
        return profileList[position].typenum
    }
}