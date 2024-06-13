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
        updateLowStockPriceSum()

    }

    private fun updateLowStockPriceSum(){
        _lowStockPriceSum.value = _items.value.orEmpty()  // Use orEmpty for safety
            .filter { it.currQuantity <= it.lowStock }
            .sumOf { it.price }

    }

    fun deleteItem(item: Item) {
        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
        items.remove(item)
        _items.value = items
        updateLowStockPriceSum()
    }

    fun addItem(item: Item) {
        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
        items.add(item)
        _items.value = items
        updateLowStockPriceSum()

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
                "Couch",
                "Pull-out queen microfiber ray",
                "living room",
                0,
                1,
                0,
                700,
            ),
            Item(
                "Chair",
                "Accent chair",
                "living room",
                0,
                2,
                1,
                200,
            ),
            Item(
                "Coffee table",
                "Metal, glass top",
                "living room",
                0,
                1,
                0,
                170,
            ),
            Item(
                "End table",
                "Metal, glass top",
                "living room",
                0,
                1,
                0,
                120,
            ),
            Item(
                "Lamp",
                "Metal standing lamp",
                "living room",
                0,
                1,
                0,
                70,
            ),
            Item(
                "TV stand",
                "wood",
                "living room",
                0,
                1,
                0,
                100,
            ),
            Item(
                "TV",
                "50 inch",
                "living room",
                0,
                1,
                0,
                400,
            ),
            Item(
                "Bar stools",
                "breakfast bar stools",
                "kitchen",
                0,
                3,
                2,
                80,
            ),
            Item(
                "Coffee maker",
                "Black and Decker",
                "kitchen",
                0,
                1,
                0,
                40,
            ),
            Item(
                "Dish set",
                "Set of plates, bowels, coffee mugs",
                "kitchen",
                0,
                8,
                4,
                80,
            ),
            Item(
                "Silverware",
                "Set of forks, knives, spoons",
                "kitchen",
                0,
                8,
                4,
                60,
            ),
            Item(
                "Queen Bed",
                "Platform with matress",
                "bedroom",
                0,
                1,
                0,
                900,
            ),
            Item(
                "Dresser",
                "48 inch wide",
                "bedroom",
                0,
                1,
                0,
                400,
            ),
            Item(
                "TV",
                "30 inch",
                "bedroom",
                0,
                1,
                0,
                250,
            ),
            Item(
                "Sheet set",
                "Set of flat, fitted, pillow cases white",
                "bedroom",
                0,
                4,
                3,
                40,
            ),
            Item(
                "Bath mat",
                "Carpet white",
                "bathroom",
                0,
                2,
                1,
                20,
            ),
            Item(
                "Towels",
                "Bath towels white",
                "bathroom",
                0,
                8,
                5,
                80,
            ),
            Item(
                "Washcloths",
                "white",
                "bathroom",
                0,
                8,
                5,
                40,
            )
        )
    }
}