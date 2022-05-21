package com.example.projetosmarttools.Fragment.Dash

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.pie.PieChart
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.example.projetosmarttools.R
import kotlinx.android.synthetic.main.fragment_dash.*

import androidx.recyclerview.widget.RecyclerView




class DashFragment : Fragment() {

    lateinit var pie:PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.fragment_dash, container, false)
        pie = v.findViewById(R.id.pieChart)

        val entrada = Segment("50%", 1500)
        val saida = Segment("50%", 1500)

        val entradaCor = SegmentFormatter(Color.parseColor("#107C41"))
        val saidaCor = SegmentFormatter(Color.parseColor("#C60000"))

        pie.addSegment(entrada, entradaCor)
        pie.addSegment(saida, saidaCor)


        val transaction = childFragmentManager.beginTransaction()
        val argument1 = Bundle()
        transaction.replace(R.id.fragment_details, FragmentTransacoes::class.java, null)
        transaction.commit()

        return v
//        return inflater.inflate(R.layout.fragment_dash, container, false)
    }
}