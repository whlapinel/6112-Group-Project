package com.example.a6112_final_project_kotlin

import ItemsRepository
import android.app.Application

class MyApplication: Application() {
    val database by lazy { ItemsDatabase.getDatabase(this) }
    val repository by lazy { ItemsRepository(database.itemsDao()) }
}