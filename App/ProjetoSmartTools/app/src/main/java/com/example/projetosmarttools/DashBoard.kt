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

class DashBoard : AppCompatActivity() {

    lateinit var pie: com.androidplot.pie.PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        pie = findViewById(R.id.pieChart)

        val entrada = Segment("50%", 1500)
        val saida = Segment("50%", 1500)

        val entradaCor = SegmentFormatter(Color.parseColor("#107C41"))
        val saidaCor = SegmentFormatter(Color.parseColor("#C60000"))

        pie.addSegment(entrada, entradaCor)
        pie.addSegment(saida, saidaCor)


        val transaction =  supportFragmentManager.beginTransaction()
        val argument1 = Bundle()
        transaction.replace(R.id.fragment_details, FragmentTransacoes::class.java, null)
        transaction.commit()

    }


}