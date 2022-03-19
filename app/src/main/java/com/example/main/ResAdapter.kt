package com.example.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.main.search.Search
import com.example.main.search.searchMainActivity
import com.google.api.Context

class ResAdapter (private val context: searchMainActivity): RecyclerView.Adapter<ResAdapter.ViewHolder>() {
    private var searchList = mutableListOf<Data>()

    fun setListData(data:MutableList<Data>){
        searchList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResAdapter.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_search_item, parent, false)
        return ViewHolder(view).apply {
            search_name.setOnClickListener {

            }
            delete_search_btn.setOnClickListener {

            }
        }
    }

    override fun onBindViewHolder(holder: ResAdapter.ViewHolder, position: Int) {
        val search : Data = searchList[position]
        holder.search_name.text = search.name
        holder.search_date.text = search.date
        holder.delete_search_btn.setImageResource(R.drawable.ic_baseline_cancel_24)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val search_name : TextView = itemView.findViewById(R.id.search_name)
        val search_date : TextView = itemView.findViewById(R.id.search_date)
        val delete_search_btn : ImageView = itemView.findViewById(R.id.delete_search_btn)
    }
}