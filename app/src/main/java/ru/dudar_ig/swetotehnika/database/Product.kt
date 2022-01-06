package ru.dudar_ig.swetotehnika.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cartitems")
data class Product(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "count") var count: Int = 1,
    @ColumnInfo(name = "price") var price: String = ""
    ): Serializable
