package com.example.projetosmarttools.Fragment.Dash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class DashFragment : Fragment() {

    lateinit var pie:PieChart
    lateinit var botaoAdd: Button
    private lateinit var sessionManager: SessionManager
    lateinit var entradaGeral: TextView
    lateinit var saidaGeral: TextView
    lateinit var saldoGeral: TextView
    lateinit var eye: ImageView

    private var setVisible: Boolean = false
    private var entradaEye: Double = 0.0
    private var saidaEye: Double = 0.0
    private var saldoEye: Double = 0.0



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pie = view.findViewById(R.id.pieChart)
        botaoAdd = view.findViewById(R.id.add)
        saidaGeral = view.findViewById(R.id.saida_geral)
        entradaGeral = view.findViewById(R.id.entrada_geral)
        saldoGeral = view.findViewById(R.id.saldo_geral)
        eye = view.findViewById(R.id.eye_icon)
        sessionManager = SessionManager(requireActivity().baseContext)

        botaoAdd.setOnClickListener {
            val novasTransacoes = Intent(view.context, NovasTransacoes::class.java)
            startActivity(novasTransacoes)
        }

        eye.setOnClickListener {
            if (!setVisible) {
                entradaGeral.text = "R$ ********"
                saidaGeral.text = "R$ ********"
                saldoGeral.text = "R$ ********"
                setVisible = true
            } else {
                entradaGeral.text = "R$ ${entradaEye}"
                saidaGeral.text = "R$ ${saidaEye}"
                saldoGeral.text = "R$ ${roundOffDecimal(saldoEye)}"
                setVisible = false
            }
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

                        val conta = response.body()!!.valorTotalReceitas - response.body()!!.valorTotalDespesas

                        saldoGeral.text = "R$ ${roundOffDecimal(conta)}"
                        entradaGeral.text = "R$ ${response.body()!!.valorTotalReceitas}"
                        saidaGeral.text = "R$ ${response.body()!!.valorTotalDespesas}"

                        entradaEye = response.body()!!.valorTotalReceitas
                        saidaEye = response.body()!!.valorTotalDespesas
                        saldoEye = conta

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

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }

    private fun opa(income: Double, outcome: Double) {

        val inPercent = (income * 100) / (income + outcome)
        val outPercent = (outcome * 100) / (income + outcome)

        val entrada = Segment("${inPercent.roundToInt()}%", income)
        val saida = Segment("${outPercent.roundToInt()}%", outcome)

        val entradaCor = SegmentFormatter(Color.parseColor("#107C41"))
        val saidaCor = SegmentFormatter(Color.parseColor("#C60000"))

        entradaCor.labelPaint.textSize = 45f
        saidaCor.labelPaint.textSize = 45f

        pie.clear()
        pie.addSegment(entrada, entradaCor)
        pie.addSegment(saida, saidaCor)
        pie.redraw()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dash, container, false)

    }


}