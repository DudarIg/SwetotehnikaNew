package ru.dudar_ig.swetotehnika.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

        @Query("SELECT * FROM cartitems")
        fun getProducts(): LiveData<List<Product>>

        @Query("SELECT * FROM cartitems WHERE id=(:id)")
        fun getProduct(id: Int): LiveData<Product?>

        @Query("SELECT COUNT() AS KOL FROM cartitems")
        fun getCount(): LiveData<Int>

        @Insert
        fun addProduct(product: Product)

        @Update
        fun updateProduct(product: Product)

        @Delete
        fun delProduct(product: Product)
}