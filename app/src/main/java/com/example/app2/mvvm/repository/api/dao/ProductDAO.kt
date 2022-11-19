package com.example.app2.mvvm.repository.api.dao

import androidx.room.*
import com.example.app2.mvvm.repository.api.model.ProductModel

@Dao
interface ProductDAO {

    @Insert
    fun insert(p: ProductModel): Long

    @Update
    fun update(p: ProductModel): Int

    @Delete
    fun delete(p: ProductModel)

    @Query("DELETE FROM Product")
    fun deleteAll()

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getById(id: Int): ProductModel
//
//    @Query("SELECT * FROM Product WHERE is_available = :av")
//    fun getAvailables(av: Int): List<ProductModel>
}