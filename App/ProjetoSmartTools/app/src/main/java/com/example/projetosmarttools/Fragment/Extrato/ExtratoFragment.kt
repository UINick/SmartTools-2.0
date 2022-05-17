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
    private lateinit var extratoList: ArrayList<ExtratoVO>
    private lateinit var newArrayList: ArrayList<ExtratoVO>

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ExtratoAdapter.ViewExtratoHolder>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val extrato1 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", true)
        val extrato2 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", true)
        val extrato3 = ExtratoVO("Titulo 1", "Resposta 1", "Titulo 2", "Resposta 2", false)
        extratoList = arrayListOf<ExtratoVO>(extrato1, extrato2, extrato3)

        recyclerView = view.findViewById(R.id.recycler_id)
        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ExtratoVO>()

        getUserData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_extrato, container, false)
    }

    private fun getUserData() {
        for(i in extratoList.indices) {
            //val extr = ExtratoVO(extratoList[i])
            //newArrayList.add(extr)
        }
    }
}