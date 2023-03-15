package com.example.stok.ui.symbol

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stok.R


class SymbolRecycleViewAdapter(private var symbolItemsList:MutableList<SymbolItem>): RecyclerView.Adapter<SymbolRecycleViewAdapter.ViewHolder>() {


    fun insertNewSymbol (newSymbol: SymbolItem) {
        Log.d("STOCK-ADDING", "insertNewSymbol: ${newSymbol.name}")
        if (!symbolItemsList.any { it.name == newSymbol.name }) {
            symbolItemsList = symbolItemsList.toMutableList().apply { add(newSymbol) }
            notifyItemInserted(symbolItemsList.lastIndex)
        }
    }

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