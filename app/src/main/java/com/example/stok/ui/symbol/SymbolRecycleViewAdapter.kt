package com.example.stok.ui.symbol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stok.R


class SymbolRecycleViewAdapter(private val symbolItemsList:List<SymbolItem>): RecyclerView.Adapter<SymbolRecycleViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = symbolItemsList[position]
        holder.nameView.text = item.name
    }


    override fun getItemCount() = symbolItemsList.size


}