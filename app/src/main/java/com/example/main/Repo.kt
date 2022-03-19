package com.example.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Repo {

    fun getData(): LiveData<MutableList<Data>> {

        val mutableData = MutableLiveData<MutableList<Data>>()
        val database = Firebase.database
        val myRef = database.getReference("searchhistory")


        myRef.addValueEventListener(object : ValueEventListener {

            val listData: MutableList<Data> = mutableListOf<Data>()

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Data::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        return mutableData
    }
}