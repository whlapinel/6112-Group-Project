package com.example.a6112_final_project_kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.a6112_final_project_kotlin.databinding.FragmentItemFormBinding


private const val CATEGORY = "category"

class AddItemFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels()

    private var _binding: FragmentItemFormBinding? = null
    private val binding get() = _binding!!

    private val TAG = "AddItemFragment"

    private var item: Item? = null

    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            @Suppress("DEPRECATION")
            category = it.getString(CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: $category")
        _binding = FragmentItemFormBinding.inflate(inflater, container, false)
        val root = binding.root

        val itemNameEditText = binding.editTextItemName
        val itemDescEditText = binding.editTextItemDesc
        val itemCategoryEditText = binding.editTextItemCategory

        val currQuantityEditText = binding.editTextCurrQty
        val lowStockEditText = binding.editTextLowStock
        val requiredEditText = binding.editTextRequired
        val priceEditText = binding.editTextPrice

        val submitButton = binding.buttonSubmit
        val cancelButton = binding.buttonCancel

        itemCategoryEditText.setText(category)

        fun goBackToItemList() {
            Log.d(TAG, "goBackToItemList: ")
            val action = AddItemFragmentDirections.actionAddItemFragmentToItemListFragment(category!!)
            findNavController().navigate(action)
        }

        submitButton.setOnClickListener {
            Log.d(TAG, "onCreateView: submit")
            if (itemNameEditText.text.isNullOrEmpty() ||
                itemDescEditText.text.isNullOrEmpty() ||
                itemCategoryEditText.text.isNullOrEmpty() ||
                currQuantityEditText.text.isNullOrEmpty() ||
                lowStockEditText.text.isNullOrEmpty() ||
                requiredEditText.text.isNullOrEmpty() ||
                priceEditText.text.isNullOrEmpty()
                ) {
                Log.d(TAG, "onCreateView: error")
                Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()

                return@setOnClickListener

            }

            item?.name = itemNameEditText.text.toString()
            item?.description = itemDescEditText.text.toString()
            item?.category = itemCategoryEditText.text.toString()
            item?.currQuantity = currQuantityEditText.text.toString().toInt()
            item?.lowStock = lowStockEditText.text.toString().toInt()
            item?.required = requiredEditText.text.toString().toInt()
            item?.price = dollarsToCents(priceEditText.text.toString())
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

        @JvmStatic
        fun newInstance(category: String) = AddItemFragment().apply {
            arguments = Bundle().apply {
                putString(CATEGORY, category)
            }
        }
    }
}