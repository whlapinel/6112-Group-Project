package com.example.a6112_final_project_kotlin

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import kotlin.concurrent.Volatile

@Database(entities = [Item::class], version = 1)
abstract class ItemsDatabase: RoomDatabase() {
    abstract fun itemsDao(): ItemsDao

    companion object {
        @Volatile
        private var INSTANCE: ItemsDatabase? = null

        fun getDatabase(context: Context): ItemsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemsDatabase::class.java,
                    "items_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}