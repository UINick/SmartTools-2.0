package com.example.projetosmarttools.Fragment.Extrato

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.CadastroExtrato.DetalheExtrato
import com.example.projetosmarttools.Clientes.DetalhesDoCliente
import com.example.projetosmarttools.R

class ExtratoAdapter(private val extratoList: ArrayList<ExtratoVO>):
    RecyclerView.Adapter<ExtratoAdapter.ViewExtratoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewExtratoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_extrato, parent, false)
        return ViewExtratoHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewExtratoHolder, position: Int) {

        val currentItem = extratoList[position]

        holder.idExtrato = currentItem.id
        holder.valor.setText(currentItem.valor.toString())
        holder.data.setText(currentItem.dataRegistro)


        if (currentItem.tipoLancamento == "RECEITA") {
            holder.viewLateral.setBackgroundResource(R.drawable.view_redonda_verde)
        } else {
            holder.viewLateral.setBackgroundResource(R.drawable.view_redonda_vermelho)
        }
    }

    override fun getItemCount(): Int {
        println("THE SIZE OF THE ARRAY IS ========= ${extratoList.size}")
        return  extratoList.size
    }

    class ViewExtratoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val data: TextView = itemView.findViewById(R.id.lblSecond)
        val valor: TextView = itemView.findViewById(R.id.txtSecond)
        val viewLateral: LinearLayout = itemView.findViewById(R.id.view_lateral)
        val cell: LinearLayout = itemView.findViewById(R.id.cellExtrato)
        var idExtrato: Int = 0

        init {
            this.cell.setOnClickListener {
                val detalheExtrato = Intent(it.context, DetalheExtrato::class.java)
                detalheExtrato.putExtra("idExtrato", idExtrato)
                it.context.startActivity(detalheExtrato)
            }
        }
    }
}