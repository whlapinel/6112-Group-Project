package com.example.a6112_final_project_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a6112_final_project_kotlin.databinding.ItemRowBinding

class ItemAdapter (
    private val itemClickListener: (Item) -> Unit
): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    // view binding
    private lateinit var binding: ItemRowBinding
    private var items = emptyList<Item>()



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // view binding
        val binding = ItemRowBinding.bind(itemView)
        val textViewName = binding.textViewName
        val textViewQty = binding.textViewQty
        val textViewCategory = binding.textViewCategory
        val textViewPrice = binding.textViewPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textViewName.text = item.name
        holder.textViewQty.text = item.currQuantity.toString()
        holder.textViewCategory.text = item.category
        val dollars = centsToDollars(item.price)
        holder.textViewPrice.text = dollars
        holder.itemView.setOnClickListener { itemClickListener(item) }
    }
}