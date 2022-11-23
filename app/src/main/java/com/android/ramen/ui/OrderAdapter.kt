package com.android.ramen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.ramen.R

class OrderAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Ramen> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RamenViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RamenViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        private val water: TextView = binding.findViewById(R.id.waterInfo)
        fun bind(item: Ramen) {
            water.text = "물의 양 : ${item.water}"
        }
    }

    companion object Factory {
        fun create(parent: ViewGroup): RamenViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_ramen, parent, false)
            return RamenViewHolder(view)
        }
    }

    fun setOrderList(item: List<Ramen>) {
        this.items = item
        notifyDataSetChanged()
    }

}