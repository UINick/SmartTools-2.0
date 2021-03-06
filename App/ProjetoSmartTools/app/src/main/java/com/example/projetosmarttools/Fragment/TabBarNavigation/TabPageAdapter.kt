package com.example.projetosmarttools.Fragment.TabBarNavigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.projetosmarttools.Clientes.ClienteFragment
import com.example.projetosmarttools.Fragment.Dash.DashFragment
import com.example.projetosmarttools.Fragment.Extrato.ExtratoFragment
import com.example.projetosmarttools.Fragment.Servicos.ServicosFragment

class TabPageAdapter (activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClienteFragment()
            1 -> ExtratoFragment()
            2 -> DashFragment()
            3 -> ServicosFragment()
            else -> DashFragment()
        }
    }

}