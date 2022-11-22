package com.android.ramen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.ramen.Order
import com.android.ramen.R
import com.android.ramen.Ramen

class OrderAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Order> = emptyList()

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RamenViewHolder -> {
                holder.bind(items[position] as Ramen)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RamenViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        //        private val
        fun bind(item: Ramen) {
            binding.apply {

            }
        }
    }

    companion object Factory {
        fun create(parent: ViewGroup): RamenViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.activity_main, parent, false)
            return RamenViewHolder(view)
        }
    }

    fun setOrderList(item: List<Order>) {
        this.items = item
        notifyDataSetChanged()
    }
}