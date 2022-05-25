package com.example.projetosmarttools.Fragment.Cliente

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.CadastroCliente.CadastroDoCliente
import com.example.projetosmarttools.Fragment.Cliente.Service.ClienteService
import com.example.projetosmarttools.Fragment.Extrato.ExtratoAdapter
import com.example.projetosmarttools.Fragment.Extrato.ExtratoVO
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.Login.Service.LoginMecanico
import com.example.projetosmarttools.Login.Service.LogingResponse
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Service.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ClienteVO>
    private lateinit var sessionManager: SessionManager
    private lateinit var btnCadastrarCliente: Button

    private lateinit var llCadastrar: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireActivity().baseContext)
        println("Teste do session ${sessionManager.fetchAuthToken()}")

        newArrayList = arrayListOf<ClienteVO>()

        LoadingScreen.displayLoadingWithText(context, "", false)

        val request = ClienteService.getAllClients()
        request.fetchClients(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<List<ClienteVO>>{

                override fun onResponse(call: Call<List<ClienteVO>>, response: Response<List<ClienteVO>>) {
                    if (response.code() == 200) {
                        response.body()?.forEach { cliente ->
                            newArrayList.add(cliente)
                        }
                        getUserData()
                        LoadingScreen.hideLoading()
                    } else {
                        LoadingScreen.hideLoading()
                        getUserData()
                        Toast.makeText(activity?.baseContext, "Tenta dnv pfv", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ClienteVO>>, t: Throwable) {
                    println("ESSE É O ERRO DO CLIENTE =====> ${t.message}")
                    println("ESSE É O ERRO DO CLIENTE =====> ${t.cause}")
                    println("ESSE É O ERRO DO CLIENTE =====> ${t.localizedMessage}")
                    println("ESSE É O ERRO DO CLIENTE =====> ${t.stackTrace}")
                    LoadingScreen.hideLoading()
                    Toast.makeText(activity?.baseContext, "Deu erro =(", Toast.LENGTH_SHORT).show()
                }

            })

        llCadastrar = view.findViewById(R.id.ll_cadastrar)
        recyclerView = view.findViewById(R.id.recycler_cliente_id)
        btnCadastrarCliente = view.findViewById(R.id.btn_CadastrarCliente)

        btnCadastrarCliente.setOnClickListener {
            val cadastrarCliente = Intent(view.context, CadastroDoCliente::class.java)
            startActivity(cadastrarCliente)
        }

        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        recyclerView.setHasFixedSize(true)

    }

    private fun getUserData() {
        if (newArrayList.isEmpty()) {
            recyclerView.visibility = View.GONE
            llCadastrar.visibility = View.VISIBLE
        } else {
            recyclerView.adapter = ClienteAdapter(newArrayList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cliente, container, false)
    }

}