package com.example.main.fragments

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.main.DBManager
import com.example.main.R
import com.example.main.databinding.FragmentFirstBinding
import com.example.main.databinding.FragmentSecBinding
import com.example.main.databinding.FragmentThirdBinding
import com.example.main.testExplain

class ThirdFragment : Fragment() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    //Activity에서 값을 넘겨받기 위해 Binding을 선
    private var _binding : FragmentThirdBinding? = null
    private val binding get() = _binding!!

    var testExplain : testExplain? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        testExplain = context as testExplain
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        //binding을 이용해 받아온 값을 프레그먼트 안에있는 TextView에 작성하는 코드
        var data  = (activity as testExplain).z
        binding.original.text = data

        //링크를 클릭했을 시 해당 장학금 공지 페이지로 넘어가는 Intent 코드
        binding.original.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data))
            startActivity(Intent.createChooser(intent, "Browser"))
        }
        return binding.root
    }
}
