package com.example.a6112_final_project_kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    fun updateItem(item: Item) {
        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
        val index = items.indexOf(item)
        if (index != -1) {
            items.removeAt(index)
            items.add(item)
            _items.value = items
        }
    }

    init {
        _items.value = listOf(
            Item(
                "Item 1",
                "Description 1",
                2,
                1,
                10,
                5,
            ),
            Item(
                "Item 2",
                "Description 2",
                2,
                2,
                10,
                5,
            ),
            Item(
                "Item 3",
                "Description 3",
                2,
                3,
                10,
                5,
            ),
            Item(
                "Item 4",
                "Description 4",
                2,
                4,
                10,
                5,
            )
        )
    }
}