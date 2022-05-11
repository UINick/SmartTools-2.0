package com.example.projetosmarttools

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.FragmentContainerView
import com.androidplot.pie.PieRenderer
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.androidplot.pie.SegmentRegistry
import com.example.projetosmarttools.Fragment.Dash.FragmentTransacoes
import com.example.projetosmarttools.Fragment.TabBarNavigation.DashBoardFragment

class DashBoard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val transaction =  supportFragmentManager.beginTransaction()
        val argument1 = Bundle()
        transaction.replace(R.id.fragDash, DashBoardFragment::class.java, null)
        transaction.commit()

    }


}