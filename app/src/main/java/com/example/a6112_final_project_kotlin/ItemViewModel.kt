package com.example.a6112_final_project_kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    private val _lowStockPriceSum = MutableLiveData<Int>(0) // Start with 0
    val lowStockPriceSum: LiveData<Int> get() = _lowStockPriceSum


    fun updateItem(item: Item) {
        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
        val index = items.indexOf(item)
        if (index != -1) {
            items.removeAt(index)
            items.add(item)
            _items.value = items
        }

        _lowStockPriceSum.value = _items.value.orEmpty()  // Use orEmpty for safety
            .filter { it.currQuantity <= it.lowStock }
            .sumOf { it.price }
    }

    fun deleteItem(item: Item) {
        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
        items.remove(item)
        _items.value = items
    }

    fun addItem(item: Item) {
        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
        items.add(item)
        _items.value = items
    }

    fun getAllCategories(): List<String> {
        val items = _items.value.orEmpty() // Handle null case safely
        return items.map { it.category }.distinct()
    }

    fun getItemsByCategory(category: String): List<Item> {
        val items = _items.value.orEmpty() // Handle null case safely
        return items.filter { it.category == category }
    }

    init {
        _items.value = listOf(
            Item(
                "Item 1",
                "Description 1",
                "kitchen",
                1,
                10,
                5,
                800,
            ),
            Item(
                "Item 2",
                "Description 2",
                "living room",
                2,
                10,
                5,
                700,
            ),
            Item(
                "Item 3",
                "Description 3",
                "bedroom",
                3,
                10,
                5,
                600,
            ),
            Item(
                "Item 4",
                "Description 4",
                "bathroom",
                4,
                10,
                5,
                500,
            )
        )
    }
}