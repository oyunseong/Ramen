package com.android.ramen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.ramen.R

class OrderAdapter(private val onItemClickListener: (Ramen) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Ramen> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return create(parent, onItemClickListener)
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

    class RamenViewHolder(
        private val binding: View,
        private val onItemClickListener: (Ramen) -> Unit
    ) :
        RecyclerView.ViewHolder(binding) {
        private val orderNumber: TextView = binding.findViewById(R.id.orderNumber)
        private val water: TextView = binding.findViewById(R.id.waterInfo)
        private val powder: TextView = binding.findViewById(R.id.powderInfo)
        private val etc: TextView = binding.findViewById(R.id.etcInfo)

        fun bind(item: Ramen) {
            binding.rootView.setOnClickListener {
                onItemClickListener.invoke(item)
            }
            orderNumber.text = "주문번호 : ${item.orderNumber}"
            water.text = "물의 양 : ${item.water}ml"
            powder.text = "파우더 양 : ${item.powder}%"
            etc.text = "추가 재료 : ${item.etc}"
        }
    }

    companion object Factory {
        fun create(parent: ViewGroup, onItemClickListener: (Ramen) -> Unit): RamenViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_ramen, parent, false)
            return RamenViewHolder(view, onItemClickListener)
        }
    }

    fun setOrderList(item: List<Ramen>) {
        this.items = item
        notifyDataSetChanged()
    }

}