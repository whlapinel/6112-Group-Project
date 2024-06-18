package com.example.a6112_final_project_kotlin

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface ItemsDao {
    @Insert
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("SELECT * FROM items")
    fun getAll(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getById(id: Long): Item?

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}