package com.example.a6112_final_project_kotlin

import ItemsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemsRepository) : ViewModel() {
    val items: LiveData<List<Item>> get() = repository.allItems

    private val _lowStockPriceSum = MutableLiveData<Int>(0) // Start with 0
    val lowStockPriceSum: LiveData<Int> get() = _lowStockPriceSum


    fun updateItem(item: Item) {
        viewModelScope.launch {
            repository.update(item)
            updateLowStockPriceSum()
        }
//        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
//        val index = items.indexOf(item)
//        if (index != -1) {
//            items.removeAt(index)
//            items.add(item)
//            _items.value = items
//        }
//        updateLowStockPriceSum()
//
    }

    private fun updateLowStockPriceSum() {
        _lowStockPriceSum.value = items.value.orEmpty()  // Use orEmpty for safety
            .filter { it.currQuantity <= it.lowStock }.sumOf { it.price }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.delete(item)
            updateLowStockPriceSum()
        }
//        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
//        items.remove(item)
//        _items.value = items
//        updateLowStockPriceSum()
    }

    fun addItem(item: Item) {
        viewModelScope.launch {
            repository.insert(item)
            updateLowStockPriceSum()
        }
//        val items = _items.value.orEmpty().toMutableList() // Handle null case safely
//        items.add(item)
//        _items.value = items
//        updateLowStockPriceSum()
//
    }

    fun getAllCategories(): List<String> {
        val items = items.value.orEmpty() // Handle null case safely
        return items.map { it.category }.distinct()
    }

    fun getItemsByCategory(category: String): List<Item> {
        val items = items.value.orEmpty() // Handle null case safely
        return items.filter { it.category == category }
    }

    init {
//        viewModelScope.launch {
//            repository.insert(
//                Item(
//                    0,
//                    "Couch",
//                    "Pull-out queen microfiber ray",
//                    "living room",
//                    0,
//                    1,
//                    0,
//                    700,
//                ), Item(
//                    0,
//                    "Couch",
//                    "Pull-out queen microfiber ray",
//                    "living room",
//                    0,
//                    1,
//                    0,
//                    700,
//                ), Item(
//                    0,
//                    "Chair",
//                    "Accent chair",
//                    "living room",
//                    0,
//                    2,
//                    1,
//                    200,
//                ), Item(
//                    0,
//                    "Coffee table",
//                    "Metal, glass top",
//                    "living room",
//                    0,
//                    1,
//                    0,
//                    170,
//                ), Item(
//                    0,
//                    "End table",
//                    "Metal, glass top",
//                    "living room",
//                    0,
//                    1,
//                    0,
//                    120,
//                ), Item(
//                    0,
//                    "Lamp",
//                    "Metal standing lamp",
//                    "living room",
//                    0,
//                    1,
//                    0,
//                    70,
//                ), Item(
//                    0,
//                    "TV stand",
//                    "wood",
//                    "living room",
//                    0,
//                    1,
//                    0,
//                    100,
//                ), Item(
//                    0,
//                    "TV",
//                    "50 inch",
//                    "living room",
//                    0,
//                    1,
//                    0,
//                    400,
//                ), Item(
//                    0,
//                    "Bar stools",
//                    "breakfast bar stools",
//                    "kitchen",
//                    0,
//                    3,
//                    2,
//                    80,
//                ), Item(
//                    0,
//                    "Coffee maker",
//                    "Black and Decker",
//                    "kitchen",
//                    0,
//                    1,
//                    0,
//                    40,
//                ), Item(
//                    0,
//                    "Dish set",
//                    "Set of plates, bowels, coffee mugs",
//                    "kitchen",
//                    0,
//                    8,
//                    4,
//                    80,
//                ), Item(
//                    0,
//                    "Silverware",
//                    "Set of forks, knives, spoons",
//                    "kitchen",
//                    0,
//                    8,
//                    4,
//                    60,
//                ), Item(
//                    0,
//                    "Queen Bed",
//                    "Platform with matress",
//                    "bedroom",
//                    0,
//                    1,
//                    0,
//                    900,
//                ), Item(
//                    0,
//                    "Dresser",
//                    "48 inch wide",
//                    "bedroom",
//                    0,
//                    1,
//                    0,
//                    400,
//                ), Item(
//                    0,
//                    "TV",
//                    "30 inch",
//                    "bedroom",
//                    0,
//                    1,
//                    0,
//                    250,
//                ), Item(
//                    0,
//                    "Sheet set",
//                    "Set of flat, fitted, pillow cases white",
//                    "bedroom",
//                    0,
//                    4,
//                    3,
//                    40,
//                ), Item(
//                    0,
//                    "Bath mat",
//                    "Carpet white",
//                    "bathroom",
//                    0,
//                    2,
//                    1,
//                    20,
//                ), Item(
//                    0,
//                    "Towels",
//                    "Bath towels white",
//                    "bathroom",
//                    0,
//                    8,
//                    5,
//                    80,
//                ), Item(
//                    0,
//                    "Washcloths",
//                    "white",
//                    "bathroom",
//                    0,
//                    8,
//                    5,
//                    40,
//                )
//            )
//        }
    }
}