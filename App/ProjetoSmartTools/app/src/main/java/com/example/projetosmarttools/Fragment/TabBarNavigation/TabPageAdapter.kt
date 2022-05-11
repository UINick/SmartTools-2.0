package com.example.projetosmarttools.Fragment.TabBarNavigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.projetosmarttools.*

class TabPageAdapter (activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClienteFragment()
            1 -> ExtratoFragment()
            2 -> DashBoardFragment()
            3 -> ServicosFragment()
            4 -> AjustesFragment()
            else -> DashBoardFragment()
        }
    }

}