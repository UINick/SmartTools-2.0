package com.example.projetosmarttools.Fragment.Cliente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.Fragment.Extrato.ExtratoAdapter
import com.example.projetosmarttools.Fragment.Extrato.ExtratoVO
import com.example.projetosmarttools.R

class ClienteAdapter(private val clienteList: ArrayList<ClienteVO>):
    RecyclerView.Adapter<ClienteAdapter.ViewClienteHolder>() {


    class ViewClienteHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
            }
        }

        val nome: TextView = itemView.findViewById(R.id.lblSecond)
        val telefone: TextView = itemView.findViewById(R.id.txtSecond)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewClienteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_cliente, parent, false)
        return ClienteAdapter.ViewClienteHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewClienteHolder, position: Int) {
        val currentItem = clienteList[position]

        holder.nome.setText(currentItem.nome)
        holder.telefone.setText(currentItem.telefone)
    }

    override fun getItemCount(): Int {
        return clienteList.size
    }

}