package com.example.main.fragments

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.main.DBManager
import com.example.main.R
import com.example.main.databinding.FragmentFirstBinding
import com.example.main.databinding.FragmentSecBinding
import com.example.main.testExplain

class SecFragment : Fragment() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase
    //Activity에서 값을 받아오기 위해 Binding 선
    private var _binding : FragmentSecBinding? = null
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
        _binding = FragmentSecBinding.inflate(inflater, container, false)

        //받아온 값을 binding을 이용하여 TextView에 작성하는 코드
        var data  = (activity as testExplain).y
        binding.qualifyText.text = data
        return binding.root
    }
}