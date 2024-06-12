package com.example.a6112_final_project_kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.a6112_final_project_kotlin.databinding.FragmentItemFormBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ITEM = "item"

/**
 * A simple [Fragment] subclass.
 * Use the [EditItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditItemFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels()

    private var _binding: FragmentItemFormBinding? = null
    private val binding get() = _binding!!

    private val TAG = "EditItemFragment"

    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            @Suppress("DEPRECATION")
            item = it.getParcelable(ITEM)
        }
        Log.d(TAG, "onCreate: " + item?.name)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: " + item?.name)
        _binding = FragmentItemFormBinding.inflate(inflater, container, false)
        val root = binding.root

        val itemNameEditText = binding.editTextItemName
        val itemDescEditText = binding.editTextItemDesc
        val itemCategoryEditText = binding.editTextItemCategory

        val currQuantityEditText = binding.editTextCurrQty
        val lowStockEditText = binding.editTextLowStock
        val requiredEditText = binding.editTextRequired
        val submitButton = binding.buttonSubmit
        val cancelButton = binding.buttonCancel

        itemNameEditText.setText(item?.name)
        itemDescEditText.setText(item?.description)
        itemCategoryEditText.setText(item?.category)
        currQuantityEditText.setText(item?.currQuantity.toString())
        lowStockEditText.setText(item?.lowStock.toString())
        requiredEditText.setText(item?.required.toString())

        fun goBackToItemList() {
            val action = EditItemFragmentDirections.actionEditItemFragmentToItemListFragment()
            findNavController().navigate(action)
        }

        submitButton.setOnClickListener {
            item?.name = itemNameEditText.text.toString()
            item?.description = itemDescEditText.text.toString()
            item?.category = itemCategoryEditText.text.toString()
            item?.currQuantity = currQuantityEditText.text.toString().toInt()
            item?.lowStock = lowStockEditText.text.toString().toInt()
            item?.required = requiredEditText.text.toString().toInt()
            viewModel.updateItem(item!!)
            goBackToItemList()
        }

        cancelButton.setOnClickListener { goBackToItemList() }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding to avoid memory leaks
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment EditItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(item: Item) = EditItemFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ITEM, item)
            }
        }
    }
}