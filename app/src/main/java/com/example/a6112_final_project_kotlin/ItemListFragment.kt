package com.example.a6112_final_project_kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a6112_final_project_kotlin.databinding.FragmentItemListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CATEGORY = "category"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemListFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels()

    private final val TAG: String = "ItemListFragment:"
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: $category")
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        val root = binding.root

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        itemAdapter = ItemAdapter() { item ->
            Log.d(TAG, "Item clicked: ${item.name}")
            // TODO: change navigation to ItemDetailsFragment
            val action = ItemListFragmentDirections.actionItemListFragmentToEditItemFragment(item)
            // navigate to EditItemFragment with the selected item
            findNavController().navigate(action)
        }
        recyclerView.adapter = itemAdapter
        viewModel.items.observe(viewLifecycleOwner) { items ->
            Log.d(TAG, "items: ${items.get(0).category}")
            items.filter { it.category == category }.let { filteredItems ->
                itemAdapter.updateItems(filteredItems)
            }
        }
        addButton = binding.addButton
        addButton.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(category!!)
            findNavController().navigate(action)
        }
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param category String.
         * @return A new instance of fragment ItemListFragment.
         */

        @JvmStatic
        fun newInstance(category: String) =
            ItemListFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY, category)
                }
            }
    }
}