package com.example.projetosmarttools.Servicos

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.R
import kotlin.collections.ArrayList

class ServicosAdapter(private val servicoList: ArrayList<ServicoDetailsVO>):
    RecyclerView.Adapter<ServicosAdapter.ViewServicoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewServicoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_servico, parent, false)
        return ViewServicoHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewServicoHolder, position: Int) {
        val currentItem = servicoList[position]

        holder.idServico = currentItem.id
        holder.ordem.setText(currentItem.ordemServico.toString())
        holder.placa.setText(currentItem.descricao)
        holder.dataServico.setText(currentItem.dataServico)

        if (currentItem.statusServico == "PENDENTE") {
            holder.status.setText(currentItem.statusServico)
            holder.llStatus.setBackgroundResource(R.drawable.view_redonda_nao_iniciado)
        } else if (currentItem.statusServico == "EM_ANDAMENTO") {
            val teste = currentItem.statusServico.split("_")
            val teste2 = teste[0] + " " + teste[1]
            holder.status.setText(teste2)
            holder.llStatus.setBackgroundResource(R.drawable.view_redonda_em_andamento)
        } else {
            holder.status.setText(currentItem.statusServico)
            holder.llStatus.setBackgroundResource(R.drawable.view_redonda_finalizado)
        }
    }

    override fun getItemCount(): Int {
        println("Quantidade de servicos ====> ${servicoList.size}")
        return servicoList.size
    }

    class ViewServicoHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var idServico: Int = 0
        val ordem: TextView = itemView.findViewById(R.id.tv_ordem_resposta)
        val placa: TextView = itemView.findViewById(R.id.tv_placa_resposta)
        val dataServico: TextView = itemView.findViewById(R.id.tv_data_resposta)
        val status: TextView = itemView.findViewById(R.id.tv_status_resposta)
        val llStatus: View = itemView.findViewById(R.id.view_status)
        val btn: Button = itemView.findViewById(R.id.btn_status)

        init {
            this.btn.setOnClickListener {
                val detalheCliente = Intent(it.context, AlterarStatusActivity::class.java)
                detalheCliente.putExtra("idServico", idServico)
                it.context.startActivity(detalheCliente)
            }
        }
    }

}