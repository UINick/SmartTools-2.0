package com.example.projetosmarttools.Clientes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.Clientes.CadastroCliente.DetalheCliente
import com.example.projetosmarttools.R

class ClienteAdapter(private val clienteList: ArrayList<ClienteVO>):
    RecyclerView.Adapter<ClienteAdapter.ViewClienteHolder>() {


    class ViewClienteHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var idCliente: Int = 0
        val nome: TextView = itemView.findViewById(R.id.lblSecond)
        val telefone: TextView = itemView.findViewById(R.id.txtSecond)
        val cell: View = itemView.findViewById(R.id.ll_view)

        init {
            this.cell.setOnClickListener {
                val detalheCliente = Intent(it.context, DetalhesDoCliente::class.java)
                detalheCliente.putExtra("idCliente", idCliente)
                it.context.startActivity(detalheCliente)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewClienteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_cliente, parent, false)
        return ViewClienteHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewClienteHolder, position: Int) {
        val currentItem = clienteList[position]
        holder.idCliente = currentItem.id
        holder.nome.setText(currentItem.nome)
        holder.telefone.setText(currentItem.telefone)
    }

    override fun getItemCount(): Int {
        return clienteList.size
    }

}