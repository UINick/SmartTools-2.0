package com.example.projetosmarttools.Fragment.TabBarNavigation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.example.projetosmarttools.Fragment.Dash.FragmentTransacoes
import com.example.projetosmarttools.R
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.fragment_dash_board.*

class DashBoardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        val entrada = Segment("50%", 1500)
//        val saida = Segment("50%", 1500)
//
//        val entradaCor = SegmentFormatter(Color.parseColor("#107C41"))
//        val saidaCor = SegmentFormatter(Color.parseColor("#C60000"))

//        pieChart.addSegment(entrada, entradaCor)
//        pieChart.addSegment(saida, saidaCor)


        val transaction = childFragmentManager.beginTransaction()
        val argument1 = Bundle()
        transaction.replace(R.id.fragment_details, FragmentTransacoes::class.java, null)
        transaction.commit()

    }
}