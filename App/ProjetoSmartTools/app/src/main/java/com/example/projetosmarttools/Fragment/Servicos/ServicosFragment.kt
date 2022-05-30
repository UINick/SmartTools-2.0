package com.example.projetosmarttools.Fragment.Servicos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Servicos.ServicoDetailsVO
import com.example.projetosmarttools.Servicos.ServicoService
import com.example.projetosmarttools.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ServicosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ServicoDetailsVO>
    private lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sessionManager = SessionManager(requireActivity().baseContext)
        recyclerView = view.findViewById(R.id.recycler_servicos_id)
        recyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
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

                if (response.code() == 200) {
                    newArrayList.clear()
                    response.body()!!.forEach { servico ->
                        newArrayList.add(servico)
                    }
                    getUserData()
                    LoadingScreen.hideLoading()
                }
            }

            override fun onFailure(call: Call<List<ServicoDetailsVO>>, t: Throwable) {
                LoadingScreen.hideLoading()
                Toast.makeText(activity?.baseContext, "Vish", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getUserData() {
        if(newArrayList.isEmpty()) {
            recyclerView.visibility = View.GONE
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