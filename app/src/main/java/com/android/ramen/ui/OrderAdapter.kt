package com.android.ramen.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.ramen.R

class OrderAdapter(private val onItemClickListener: (Order) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Order> = emptyList()

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
        private val onItemClickListener: (Order) -> Unit
    ) :
        RecyclerView.ViewHolder(binding) {
        private val orderNumber: TextView = binding.findViewById(R.id.orderNumber)
        private val orderImage: ImageView = binding.findViewById(R.id.orderImage)

        @SuppressLint("SetTextI18n")
        fun bind(item: Order) {
            binding.rootView.setOnClickListener {
                onItemClickListener.invoke(item)
            }
            orderNumber.text = "주문번호 : ${item.id}"
            orderImage.setImageResource(item.image)
        }
    }

    companion object Factory {
        fun create(parent: ViewGroup, onItemClickListener: (Order) -> Unit): RamenViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_ramen, parent, false)
            return RamenViewHolder(view, onItemClickListener)
        }
    }

    fun setOrderList(item: List<Order>) {
        this.items = item
        notifyDataSetChanged()
    }

}