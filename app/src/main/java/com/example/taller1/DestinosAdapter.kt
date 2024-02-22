package com.example.taller1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DestinosAdapter(private val destinos: List<String>) : RecyclerView.Adapter<DestinosAdapter.DestinoViewHolder>() {

    class DestinoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.textViewNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_destino, parent, false)
        return DestinoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DestinoViewHolder, position: Int) {
        val destino = destinos[position]
        holder.nombreTextView.text = destino
    }


    override fun getItemCount(): Int {
        return destinos.size
    }
}
