package com.example.projetosmarttools.Fragment.Dash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.FragmentContainerView
import com.androidplot.pie.PieChart
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.example.projetosmarttools.Fragment.Extrato.ExtratoService
import com.example.projetosmarttools.Fragment.Extrato.ResumoLancamentoVO
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.Login.Activities.LoginDoMecanico
import com.example.projetosmarttools.NovasTransacoes
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashFragment : Fragment() {

    lateinit var pie:PieChart
    lateinit var botaoAdd: Button
    private lateinit var sessionManager: SessionManager
    private var entradaClient: Double = 0.0
    private var saidaClient: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pie = view.findViewById(R.id.pieChart)
        botaoAdd = view.findViewById(R.id.add)
        sessionManager = SessionManager(requireActivity().baseContext)

        botaoAdd.setOnClickListener {
            val novasTransacoes = Intent(view.context, NovasTransacoes::class.java)
            startActivity(novasTransacoes)
        }


//        val transaction = activity?.supportFragmentManager!!.beginTransaction()
//
//        view.findViewById<LinearLayout>(R.id.linear_details).removeAllViews()
//
//        repeat(6) {
//            val fragmento = FragmentContainerView(view.context)
//            fragmento.id = View.generateViewId()
//            view.findViewById<LinearLayout>(R.id.linear_details).addView(fragmento)
//            transaction.add(fragmento.id, FragmentTransacoes::class.java, null)
//        }
//        transaction.commit()

    }

    override fun onResume() {
        super.onResume()
        callChartService()
    }

    private fun callChartService() {
        LoadingScreen.displayLoadingWithText(context, "", false)
        //first call: lancamentos/resumo

        val requestSummary = ExtratoService.extrato().fetchLancamentosSummary(
            token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<ResumoLancamentoVO>{
                override fun onResponse(
                    call: Call<ResumoLancamentoVO>,
                    response: Response<ResumoLancamentoVO>
                ) {
                    LoadingScreen.hideLoading()
                    if (response.code() == 200) {
                        opa(
                            income = response.body()!!.valorTotalReceitas,
                            outcome = response.body()!!.valorTotalDespesas
                        )
                    }

                }

                override fun onFailure(call: Call<ResumoLancamentoVO>, t: Throwable) {

                }
            })
    }

    private fun opa(income: Double, outcome: Double) {
        val inPercent = (income * 100) / (income + outcome)
        val outPercent = (outcome * 100) / (income + outcome)

        val entrada = Segment("${inPercent}%", income)
        val saida = Segment("${outPercent}%", outcome)

        val entradaCor = SegmentFormatter(Color.parseColor("#107C41"))
        val saidaCor = SegmentFormatter(Color.parseColor("#C60000"))

        entradaCor.labelPaint.textSize = 100f
        saidaCor.labelPaint.textSize = 100f

        pie.addSegment(entrada, entradaCor)
        pie.addSegment(saida, saidaCor)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dash, container, false)

    }


}