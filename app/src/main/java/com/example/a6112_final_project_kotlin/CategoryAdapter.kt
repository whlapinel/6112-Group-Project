package com.example.a6112_final_project_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a6112_final_project_kotlin.databinding.CategoryRowBinding
import com.example.a6112_final_project_kotlin.databinding.ItemRowBinding

class CategoryAdapter (
    private var categories : List<String>,
    private val itemClickListener: (String) -> Unit
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(categoryView: View) : RecyclerView.ViewHolder(categoryView) {
        // view binding
        private val binding = CategoryRowBinding.bind(categoryView)
        val categoryTextView: TextView = binding.categoryListTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun updateItems(newCategories: List<String>) {
        categories = newCategories
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryTextView.text = category
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(category)
        }
    }
}