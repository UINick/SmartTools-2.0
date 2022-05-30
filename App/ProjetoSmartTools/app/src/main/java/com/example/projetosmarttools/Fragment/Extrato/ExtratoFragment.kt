package com.example.projetosmarttools.Fragment.Extrato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.Clientes.ClienteService
import com.example.projetosmarttools.Clientes.ClienteVO
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExtratoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var llCadastrar: LinearLayout
    private lateinit var sessionManager: SessionManager
    private lateinit var newArrayList: ArrayList<ExtratoVO>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireActivity().baseContext)

        llCadastrar = view.findViewById(R.id.ll_cadastrar)
        recyclerView = view.findViewById(R.id.recycler_id)
        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ExtratoVO>()
    }

    override fun onResume() {
        super.onResume()
        callService()
    }

    private fun callService() {
        LoadingScreen.displayLoadingWithText(context, "", false)

        val request = ExtratoService.extrato()
        request.fetchLancamentos(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<List<ExtratoVO>> {

                override fun onResponse(call: Call<List<ExtratoVO>>, response: Response<List<ExtratoVO>>) {
                    if (response.code() == 200) {
                        newArrayList.clear()
                        response.body()?.forEach { extrato ->
                            newArrayList.add(extrato)
                        }
                        getUserData()
                        LoadingScreen.hideLoading()
                    } else {
                        LoadingScreen.hideLoading()
                        getUserData()
                    }
                }

                override fun onFailure(call: Call<List<ExtratoVO>>, t: Throwable) {
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
            recyclerView.adapter = ExtratoAdapter(newArrayList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_extrato, container, false)
    }
}