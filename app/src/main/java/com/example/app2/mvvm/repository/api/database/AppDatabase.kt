package com.example.app2.mvvm.repository.api.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app2.mvvm.repository.api.dao.ProductDAO
import com.example.app2.mvvm.repository.api.model.ProductModel


@Database(entities = [ProductModel::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun ProductDAO(): ProductDAO

    companion object {
        private lateinit var INSTANCE: AppDatabase
        fun getDatabase(context: Context, name: String): AppDatabase {

            if(!::INSTANCE.isInitialized) {

                synchronized(AppDatabase::class) {

                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, name)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}