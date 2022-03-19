package com.example.main.search

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.*
import kotlinx.android.synthetic.main.activity_alarm.*

class searchMainActivity : AppCompatActivity() {

    lateinit var Word: EditText
    lateinit var Resadapter : ResAdapter

    private lateinit var adapter: ResAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_main)

        Word = findViewById(R.id.searchWord)

        adapter = ResAdapter(this)

//        val recyclerView : RecyclerView = findViewById(R.id.search_history_recycler_view)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = adapter
//        observerData()

        Word.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }
    }

    fun observerData(){
        viewModel.fetchData().observe(this, Observer {
            Resadapter.setListData(it)
            Resadapter.notifyDataSetChanged()
        })
    }
}