package com.example.a6112_final_project_kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemListFragment : Fragment() {
    private final val TAG: String = "ItemListFragment:"
    private lateinit var viewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        itemAdapter = ItemAdapter(emptyList()) { item ->
            Log.d(TAG, "Item clicked: ${item.name}")
            val action = ItemListFragmentDirections.actionItemListFragmentToEditItemFragment(item)
            // navigate to EditItemFragment with the selected item
            findNavController().navigate(action)
        }
        recyclerView.adapter = itemAdapter
        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        viewModel.items.observe(viewLifecycleOwner) { items ->
            itemAdapter.updateItems(items)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}