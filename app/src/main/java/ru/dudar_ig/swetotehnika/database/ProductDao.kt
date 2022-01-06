package ru.dudar_ig.swetotehnika.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

        @Query("SELECT * FROM cartitems")
        fun getProducts(): LiveData<List<Product>>

        @Query("SELECT * FROM cartitems WHERE id=(:id)")
        fun getProduct(id: Int): LiveData<Product?>

        @Insert
        fun addProduct(product: Product)

        @Delete
        fun delProduct(product: Product)
}