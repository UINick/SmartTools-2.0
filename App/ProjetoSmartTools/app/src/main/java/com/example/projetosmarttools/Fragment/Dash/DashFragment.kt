package com.example.projetosmarttools.Fragment.Dash

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentContainerView
import com.androidplot.pie.PieChart
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.example.projetosmarttools.R


class DashFragment : Fragment() {

    lateinit var pie:PieChart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pie = view.findViewById(R.id.pieChart)

        val entrada = Segment("50%", 1500)
        val saida = Segment("50%", 1500)

        val entradaCor = SegmentFormatter(Color.parseColor("#107C41"))
        val saidaCor = SegmentFormatter(Color.parseColor("#C60000"))

        pie.addSegment(entrada, entradaCor)
        pie.addSegment(saida, saidaCor)

        val transaction = activity?.supportFragmentManager!!.beginTransaction()

        view.findViewById<LinearLayout>(R.id.linear_details).removeAllViews()

        repeat(6) {
            val fragmento = FragmentContainerView(view.context)
            fragmento.id = View.generateViewId()
            view.findViewById<LinearLayout>(R.id.linear_details).addView(fragmento)
            transaction.add(fragmento.id, FragmentTransacoes::class.java, null)
        }
        transaction.commit()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dash, container, false)
    }
}