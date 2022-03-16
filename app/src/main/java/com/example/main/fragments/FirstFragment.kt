package com.example.main.fragments

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frag1.*
import java.util.*
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.example.main.*
import com.example.main.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    //Activity 에서 값을 받아오게 하기 위해 Binding 이용
    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding!!

    var testExplain : testExplain? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        testExplain = context as testExplain
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    //받아온 값을 binding을 이용하여 TextView에 작성하는 코드
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        var data  = (activity as testExplain).x
        binding.detailText.text = data
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

