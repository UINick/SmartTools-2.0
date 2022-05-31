package com.example.projetosmarttools.Clientes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.Util.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Util.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ClienteVO>
    private lateinit var sessionManager: SessionManager
    private lateinit var llCadastrar: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireActivity().baseContext)
        println("Teste do session ${sessionManager.fetchAuthToken()}")

        llCadastrar = view.findViewById(R.id.ll_cadastrar)
        recyclerView = view.findViewById(R.id.recycler_cliente_id)

        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ClienteVO>()

    }

    override fun onResume() {
        super.onResume()
        callService()
    }

    private fun callService() {
        LoadingScreen.displayLoadingWithText(context, "", false)

        val request = ClienteService.getAllClients()
        request.fetchClients(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<List<ClienteVO>>{

                override fun onResponse(call: Call<List<ClienteVO>>, response: Response<List<ClienteVO>>) {
                    if (response.code() == 200) {
                        newArrayList.clear()
                        response.body()?.forEach { cliente ->
                            newArrayList.add(cliente)
                        }
                        getUserData()
                        LoadingScreen.hideLoading()
                    } else {
                        LoadingScreen.hideLoading()
                        getUserData()
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