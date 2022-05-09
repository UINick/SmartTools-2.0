package com.example.projetosmarttools.Fragment.Dash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetosmarttools.Enum.EnumEntrada
import com.example.projetosmarttools.R


class FragmentTransacoes : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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