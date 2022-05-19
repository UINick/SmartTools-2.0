package com.example.projetosmarttools.Fragment.Extrato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.R

class ExtratoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ExtratoVO>

    private lateinit var arrTitle1: Array<String>
    private lateinit var arrTitle2: Array<String>
    private lateinit var arrAnswer1: Array<String>
    private lateinit var arrAnswer2: Array<String>
    private lateinit var arrIsNegative: Array<Boolean>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val extrato1 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", true)
        val extrato2 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", true)
        val extrato3 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", false)
        val extrato4 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", true)
        val extrato5 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", false)
        val extrato6 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", false)
        val extrato7 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", true)
        val extrato8 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", false)

        arrTitle1 = arrayOf(extrato1.title1, extrato2.title1, extrato3.title1,extrato4.title1,extrato5.title1,extrato6.title1, extrato7.title1,extrato8.title1)
        arrTitle2 = arrayOf(extrato1.title2, extrato2.title2, extrato3.title2, extrato4.title2,extrato5.title2,extrato6.title2, extrato7.title2,extrato8.title2)
        arrAnswer1 = arrayOf(extrato1.answer1, extrato2.answer1, extrato3.answer1, extrato4.answer1,extrato5.answer1,extrato6.answer1, extrato7.answer1,extrato8.answer1)
        arrAnswer2 = arrayOf(extrato1.answer2, extrato2.answer2, extrato3.answer2, extrato4.answer2,extrato5.answer2,extrato6.answer2, extrato7.answer2,extrato8.answer2)
        arrIsNegative = arrayOf(extrato1.isNegative, extrato2.isNegative, extrato3.isNegative,extrato4.isNegative, extrato5.isNegative, extrato6.isNegative, extrato7.isNegative, extrato8.isNegative)

        recyclerView = view.findViewById(R.id.recycler_id)
        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ExtratoVO>()

        getUserData()
    }

    private fun getUserData() {
        for (i in arrTitle1.indices) {
            val extrato = ExtratoVO(arrTitle1[i], arrTitle2[i], arrAnswer1[i], arrAnswer2[1], arrIsNegative[i])
            newArrayList.add(extrato)
        }

        recyclerView.adapter = ExtratoAdapter(newArrayList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_extrato, container, false)
    }
}