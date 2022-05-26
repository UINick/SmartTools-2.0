package com.example.projetosmarttools.Fragment.Servicos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.R


class ServicosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ServicoVO>

    private lateinit var arrOrdem: Array<String>
    private lateinit var arrPlaca: Array<String>
    private lateinit var arrData: Array<String>
    private lateinit var arrStatus: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serv1 = ServicoVO("20","ABC1234", "04/02/2022", "NÃO INICIADO")
        val serv2 = ServicoVO("20","ABC1234", "04/02/2022", "EM ANDAMENTO")
        val serv3 = ServicoVO("20","ABC1234", "04/02/2022", "FINALIZADO")

        arrOrdem = arrayOf(serv1.ordem, serv2.ordem, serv3.ordem)
        arrPlaca = arrayOf(serv1.placa, serv2.placa, serv3.placa)
        arrData = arrayOf(serv1.data, serv2.data, serv3.data)
        arrStatus = arrayOf(serv1.status, serv2.status, serv3.status)


        recyclerView = view.findViewById(R.id.recycler_servicos_id)

        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ServicoVO>()

        getUserData()

    }

    private fun getUserData() {
        for (i in arrOrdem.indices) {
            val servico = ServicoVO(arrOrdem[i], arrPlaca[i], arrData[i], arrStatus[i])
            newArrayList.add(servico)
        }
        recyclerView.adapter = ServicosAdapter(newArrayList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_servicos, container, false)
    }

}