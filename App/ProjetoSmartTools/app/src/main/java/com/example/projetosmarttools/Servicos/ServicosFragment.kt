package com.example.projetosmarttools.Servicos

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


class ServicosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ServicoDetailsVO>
    private lateinit var sessionManager: SessionManager
    private lateinit var ll_cadastrar: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sessionManager = SessionManager(requireActivity().baseContext)
        recyclerView = view.findViewById(R.id.recycler_servicos_id)
        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        ll_cadastrar = view.findViewById(R.id.ll_cadastrar)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ServicoDetailsVO>()

    }

    override fun onResume() {
        super.onResume()
        callService()
    }

    fun callService() {
        LoadingScreen.displayLoadingWithText(context, "", false)

        val request = ServicoService.servico().fetchAllServices(token = "Bearer ${sessionManager.fetchAuthToken()}")
        request.enqueue(object : Callback<List<ServicoDetailsVO>> {
            override fun onResponse(call: Call<List<ServicoDetailsVO>>, response: Response<List<ServicoDetailsVO>>) {
                println("code ======> ${response.code()}")
                if (response.code() == 200) {
                    newArrayList.clear()
                    response.body()!!.forEach { servico ->
                        newArrayList.add(servico)
                    }
                    getUserData()
                    LoadingScreen.hideLoading()
                } else {
                    LoadingScreen.hideLoading()
                }
            }

            override fun onFailure(call: Call<List<ServicoDetailsVO>>, t: Throwable) {
                LoadingScreen.hideLoading()
            }

        })
    }

    private fun getUserData() {
        if(newArrayList.isEmpty()) {
            recyclerView.visibility = View.GONE
            ll_cadastrar.visibility = View.VISIBLE
        } else {
            recyclerView.adapter = ServicosAdapter(newArrayList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_servicos, container, false)
    }

}