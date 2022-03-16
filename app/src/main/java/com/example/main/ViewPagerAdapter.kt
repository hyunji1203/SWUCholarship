package com.example.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.main.fragments.FirstFragment
import com.example.main.fragments.SecFragment
import com.example.main.fragments.ThirdFragment

//상세설명 창의 프래그 먼트들을 연결시켜주는 어댑터
class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                FirstFragment()
            }
            1->{
                SecFragment()
            }
            2->{
                ThirdFragment()
            }
            else->{
                FirstFragment()
            }

        }
    }
}