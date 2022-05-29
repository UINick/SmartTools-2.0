package com.example.projetosmarttools.Fragment.Dash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.projetosmarttools.Enum.EnumEntrada
import com.example.projetosmarttools.R


class FragmentTransacoes : Fragment() {

    lateinit var categoriaTransacao: TextView
    lateinit var valorTransacao: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriaTransacao = requireView().findViewById(R.id.categoria_transacao)
        valorTransacao = requireView().findViewById(R.id.valor_transacao)

        valorTransacao.text = arguments?.getString("valor")
        categoriaTransacao.text = arguments?.getString("categoria")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transacoes, container, false)
    }

    fun categoriasEntrada(status: EnumEntrada): String {

        when (status) {
            EnumEntrada.FUNILARIA -> return ""
            EnumEntrada.MANUTENCAO -> return ""
            EnumEntrada.PECAS -> return ""
            EnumEntrada.PINTURA -> return ""
            EnumEntrada.TROCA_OLEO -> return ""
        }
    }

    companion object {

    }
}