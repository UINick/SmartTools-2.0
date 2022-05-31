package com.example.projetosmarttools.Fragment.TabBarNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficinaVO
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.InfoService
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AjustesFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    lateinit var nome: TextView
    lateinit var cnpj: TextView
    lateinit var email: TextView
    lateinit var telefone: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nome = view.findViewById(R.id.nome_oficina_info)
        email = view.findViewById(R.id.id_email_info)
        telefone = view.findViewById(R.id.id_telefone_info)
        cnpj = view.findViewById(R.id.id_cnpj_info)

        sessionManager = SessionManager(requireActivity().baseContext)

    }

    override fun onResume() {
        super.onResume()
        callService()
    }

    fun callService() {
        LoadingScreen.displayLoadingWithText(context, "", false)

        val request = InfoService.info().buscarInfo(token ="Bearer  ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<CadastroOficinaVO> {
                override fun onResponse(
                    call: Call<CadastroOficinaVO>,
                    response: Response<CadastroOficinaVO>
                ) {
                    nome.text = getString(R.string.nome_oficina) + " " + response.body()!!.nomeOficina
                    telefone.text = getString(R.string.endereco) + " " + response.body()!!.telefone
                    email.text = getString(R.string.email) + " " + response.body()!!.email
                    cnpj.text = getString(R.string.cnpj) + " " + response.body()!!.cnpj
                    LoadingScreen.hideLoading()
                }

                override fun onFailure(call: Call<CadastroOficinaVO>, t: Throwable) {
                    LoadingScreen.hideLoading()
                }

            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajustes, container, false)
    }

}