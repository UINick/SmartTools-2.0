package com.example.projetosmarttools.Fragment.Cliente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.Fragment.Extrato.ExtratoAdapter
import com.example.projetosmarttools.Fragment.Extrato.ExtratoVO
import com.example.projetosmarttools.R

class ClienteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ClienteVO>

    private lateinit var arrNome: Array<String>
    private lateinit var arrTelefone: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cliente1 = ClienteVO("Pedro", "(11) 97404-5922")
        val cliente2 = ClienteVO("Bruna", "(11) 96041-6628")
        val cliente3 = ClienteVO("Lucas", "(11) 96071-5268")
        val cliente4 = ClienteVO("Vinicius", "(11) 99607-9686")
        val cliente5 = ClienteVO("Nicholas", "(11) 95419-9312")

        arrNome = arrayOf(cliente1.nome, cliente2.nome, cliente3.nome, cliente4.nome, cliente5.nome)
        arrTelefone = arrayOf(cliente1.telefone, cliente2.telefone, cliente3.telefone, cliente4.telefone, cliente5.telefone)

        recyclerView = view.findViewById(R.id.recycler_cliente_id)
        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ClienteVO>()

        getUserData()

    }

    private fun getUserData() {
        for (i in arrNome.indices) {
            val cliente = ClienteVO(arrNome[i], arrTelefone[i])
            newArrayList.add(cliente)
        }

        recyclerView.adapter = ClienteAdapter(newArrayList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cliente, container, false)
    }

}