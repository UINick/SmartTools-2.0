package com.example.projetosmarttools.Fragment.Extrato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetosmarttools.R

class ExtratoAdapter(private val extratoList: ArrayList<ExtratoVO>):
    RecyclerView.Adapter<ExtratoAdapter.ViewExtratoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewExtratoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_extrato, parent, false)
        return ViewExtratoHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewExtratoHolder, position: Int) {

        val currentItem = extratoList[position]
        holder.title1.setText(currentItem.title1)
        holder.title2.setText(currentItem.title2)
        holder.answer1.setText(currentItem.answer1)
        holder.answer2.setText(currentItem.answer2)
    }

    override fun getItemCount(): Int {
        return  extratoList.size
    }

    class ViewExtratoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title1: TextView = itemView.findViewById(R.id.lblFirst)
        val title2: TextView = itemView.findViewById(R.id.lblSecond)
        val answer1: TextView = itemView.findViewById(R.id.txtFirst)
        val answer2: TextView = itemView.findViewById(R.id.txtSecond)
    }
}