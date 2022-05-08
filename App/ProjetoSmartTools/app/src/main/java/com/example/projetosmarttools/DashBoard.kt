package com.example.projetosmarttools

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter

class DashBoard : AppCompatActivity() {

    lateinit var pie: com.androidplot.pie.PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        pie = findViewById(R.id.pieChart)

        val entrada = Segment("", 1500)
        val saida = Segment("", 1500)

        val entradaCor = SegmentFormatter(Color.parseColor("#107C41"))
        val saidaCor = SegmentFormatter(Color.parseColor("#C60000"))

        pie.addSegment(entrada, entradaCor)
        pie.addSegment(saida, saidaCor)
    }


}