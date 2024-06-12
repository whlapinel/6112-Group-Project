package com.example.a6112_final_project_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a6112_final_project_kotlin.databinding.ItemRowBinding

class ItemAdapter (
    private var items : List<Item>,
    private val itemClickListener: (Item) -> Unit
): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    // view binding
    private lateinit var binding: ItemRowBinding



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // view binding
        val binding = ItemRowBinding.bind(itemView)
        val textViewName = binding.textViewName
        val textViewDescription = binding.textViewDescription
        val textViewQty = binding.textViewQty
        val editBtn = binding.editItemBtn
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
        holder.textViewDescription.text = item.description
        holder.textViewQty.text = item.currQuantity.toString()
        holder.editBtn.setOnClickListener {
            itemClickListener(item)
        }
    }
}