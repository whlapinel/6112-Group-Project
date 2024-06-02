package com.example.a6112_final_project_kotlin

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ITEM = "item"

/**
 * A simple [Fragment] subclass.
 * Use the [EditItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditItemFragment : Fragment() {
    private val TAG = "EditItemFragment"

    // TODO: Rename and change types of parameters
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: " + item?.name)
        val view = inflater.inflate(R.layout.fragment_edit_item, container, false)
        view.findViewById<TextView>(R.id.textViewItemName).setText(item?.name)
        return view
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
        fun newInstance(item: Item) =
            EditItemFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM, item)
                }
            }
    }
}