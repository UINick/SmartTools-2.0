package com.example.projetosmarttools.Fragment.Servicos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.Fragment.Cliente.ClienteAdapter
import com.example.projetosmarttools.Fragment.Cliente.ClienteVO
import com.example.projetosmarttools.R

class ServicosAdapter(private val servicoList: ArrayList<ServicoVO>):
    RecyclerView.Adapter<ServicosAdapter.ViewServicoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicosAdapter.ViewServicoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_servico, parent, false)
        return ServicosAdapter.ViewServicoHolder(itemView)
    }

    override fun onBindViewHolder(holder: ServicosAdapter.ViewServicoHolder, position: Int) {
        val currentItem = servicoList[position]

        holder.ordem.setText(currentItem.ordem)
        holder.placa.setText(currentItem.placa)
        holder.dataServico.setText(currentItem.data)
        holder.status.setText(currentItem.status)

        if (holder.status.text.toString() == "NÃO INICIADO") {
            holder.llStatus.setBackgroundResource(R.drawable.view_redonda_nao_iniciado)
        } else if (holder.status.text.toString() == "EM ANDAMENTO") {
            holder.llStatus.setBackgroundResource(R.drawable.view_redonda_em_andamento)
        } else {
            holder.llStatus.setBackgroundResource(R.drawable.view_redonda_finalizado)
        }
    }

    override fun getItemCount(): Int {
        return servicoList.size
    }

    class ViewServicoHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ordem: TextView = itemView.findViewById(R.id.tv_ordem_resposta)
        val placa: TextView = itemView.findViewById(R.id.tv_placa_resposta)
        val dataServico: TextView = itemView.findViewById(R.id.tv_data_resposta)
        val status: TextView = itemView.findViewById(R.id.tv_status_resposta)
        val llStatus: LinearLayout = itemView.findViewById(R.id.ll_status)
    }

}