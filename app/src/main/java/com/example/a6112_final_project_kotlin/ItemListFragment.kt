package com.example.a6112_final_project_kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a6112_final_project_kotlin.databinding.FragmentItemListBinding

private const val CATEGORY = "category"

class ItemListFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels()

    private final val TAG: String = "ItemListFragment:"
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private var category: String? = null
    private lateinit var addButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: $category")
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        val root = binding.root

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        itemAdapter = ItemAdapter() { item ->
            Log.d(TAG, "Item clicked: ${item.name}")
            val action = ItemListFragmentDirections.actionItemListFragmentToEditItemFragment(item)
            findNavController().navigate(action)
        }
        recyclerView.adapter = itemAdapter
        viewModel.items.observe(viewLifecycleOwner) { items ->
            if (!items.isNullOrEmpty()) {
                Log.d(TAG, "items: ${items[0].category}")
            }
            if (category.isNullOrEmpty()) {
                itemAdapter.updateItems(items)
            } else {
                items.filter { it.category == category }.let { filteredItems ->
                    itemAdapter.updateItems(filteredItems)
                }
            }
        }
        addButton = binding.addButton
        addButton.setOnClickListener {
            Log.d(TAG, "Add button clicked")
            var action: NavDirections
            if (category.isNullOrEmpty()) {
                action = ItemListFragmentDirections.actionItemListFragmentToAddItemFragment("")
            } else {
                action =
                    ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(category!!)
            }
            findNavController().navigate(action)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(category: String) = ItemListFragment().apply {
            arguments = Bundle().apply {
                putString(CATEGORY, category)
            }
        }
    }
}